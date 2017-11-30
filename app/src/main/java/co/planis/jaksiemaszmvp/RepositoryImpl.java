package co.planis.jaksiemaszmvp;

class RepositoryImpl implements Repository {

    static RepositoryImpl getInstance() {
        return new RepositoryImpl();
    }

    @Override
    public String loadData() {
        return "Lorem ipsum dolor si amet";
    }

    @Override
    public void saveData(String text) {
        //just for demonstration
    }
}
