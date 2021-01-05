package core.api;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public interface IViewModel extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void bindView();
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void unBindView();
}
