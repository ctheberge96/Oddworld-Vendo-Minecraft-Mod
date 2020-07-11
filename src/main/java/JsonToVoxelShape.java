import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.minecraft.block.Block;

public class JsonToVoxelShape {
	public static void main(String[] args) {
		String filePath = "src\\main\\resources\\assets\\soda-pop\\models\\block\\vendo_top.json";
		File jsonSource = new File(filePath);
		StringBuilder builder = new StringBuilder();
		builder.append("Stream.of(");
		boolean currentlyMaking = false;
		String line;
		try (BufferedReader sourceReader = new BufferedReader(new FileReader(jsonSource));) {
			while ((line = sourceReader.readLine()) != null) {
				if (line.contains("\"from\": [")) {
					if (currentlyMaking == false) {
						currentlyMaking = true;
						builder.append("\n");
						builder.append("Block.makeCuboidShape(");
					}
					// Start
					builder.append(line.substring(line.indexOf('[')+1, line.indexOf(']')));
					builder.append(", ");
				} else if (line.contains("\"to\": [")) {
					builder.append(line.substring(line.indexOf('[')+1, line.indexOf(']')));
					builder.append("), ");
					currentlyMaking = false;
				}
			}
		} catch (IOException e) { return; }
		builder.delete(builder.length()-2, builder.length());
		builder.append(")\n.reduce((v1,v2)->{return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();");
		System.out.println(builder.toString());
	}

}
