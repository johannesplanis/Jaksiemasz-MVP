package co.planis.jaksiemaszmvp;

import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

class PresenterImpl implements Contract.Presenter {

    private Repository repository;

    PresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Nullable
    private Contract.View view;

    @Override
    public void loadData() {
        String initialText = repository.loadData();

        if (view != null) view.initInput(initialText);
    }

    @Override
    public void textChanged(String text) {

    }

    private int countWords(String text) {
        return text.equals("") ? 0 : text.split(" ").length;
    }

    private String resolveSingularPluralLabel(int count) {
        return count == 1 ? " word" : " words";
    }

    @Subscribe
    public void textChanged(TextChangedEvent event){
        int wordCount = countWords(event.getText());
        String label = Integer.toString(wordCount) + resolveSingularPluralLabel(wordCount);

        if (view != null) view.updateWordCount(label);
    }

    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachView() {
        this.view = null;
        EventBus.getDefault().unregister(this);
    }
}
