package com.warven22.sodapop.utils;

import java.sql.ClientInfoStatus;
import java.util.LinkedList;

public class Event {
    /**
     * A listener that can handle an
     * {@link Event}'s invocation
     */
    public interface EventListener {
        /**
         * Handles the invocation of any {@link Event} this
         * listener is added to.
         * <br>Can be called multiple times if this listener
         * is added to multiple {@link Event}s.
         *
         * @param args The bundle the {@link Event} gave to this listener
         *             when it was invoked
         */
        public void HandleEvent();
    }

    private LinkedList<EventListener> _listeners;

    /**
     * Adds a given {@link EventListener} to this event.
     * <br> The {@link EventListener} will be called upon all subsequent
     * invocations of this event.
     * <br>The same {@link EventListener} can only be added once. Any further additions
     * while it's within this {@link Event} will be ignored.
     *
     * @param listener
     */
    public void addListener(EventListener listener) {
        if (!_listeners.contains(listener)) {
            _listeners.add(listener);
        }
    }

    /**
     * Removes a given {@link EventListener} from this event, ensuring
     * it won't be called upon when this event is invoked.
     * <br>The {@link EventListener} will only be removed if it's already
     * in the event, otherwise the removal is ignored.
     *
     * @param listener The {@link EventListener} to remove from this event
     */
    public void removeListener(EventListener listener) {
        if (_listeners.contains(listener)) {
            _listeners.remove(listener);
        }
    }

    public Event() {
        _listeners = new LinkedList<>();
    }

    /**
     * Invokes this event with the given bundle, calling
     * each {@link EventListener}'s handler event in the order the {@link EventListener}s
     * were added.
     * <br>The bundle will be given to each {@link EventListener}.
     *
     * @param bundle The bundle with data to distribute
     */
    public void invoke() {
        for (EventListener listener : _listeners) {
            listener.HandleEvent();
        }
    }
}
