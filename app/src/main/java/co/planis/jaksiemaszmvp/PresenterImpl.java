package co.planis.jaksiemaszmvp;

import android.support.annotation.Nullable;

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
        int wordCount = countWords(text);
        String label = Integer.toString(wordCount) + resolveSingularPluralLabel(wordCount);

        if (view != null) view.updateWordCount(label);
    }

    private int countWords(String text) {
        return text.equals("") ? 0 : text.split(" ").length;
    }

    private String resolveSingularPluralLabel(int count) {
        return count == 1 ? " word" : " words";
    }

    @Override
    public void attachView(Contract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
