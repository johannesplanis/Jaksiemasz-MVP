package co.planis.jaksiemaszmvp;

interface Contract {
    interface View {
        void updateWordCount(String text);
        void initInput(String text);
    }

    interface Presenter {
        void textChanged(String text);
        void attachView(Contract.View view);
        void detachView();
        void loadData();
    }
}
