package co.planis.jaksiemaszmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter presenter = new PresenterImpl(RepositoryImpl.getInstance());

    private TextView wordCount;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initPresenter();
    }

    private void initUi() {
        wordCount = findViewById(R.id.word_count);
        input = findViewById(R.id.input);

        input.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        EventBus.getDefault().post(new TextChangedEvent(s.toString()));
                       // presenter.textChanged(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );
    }

    private void initPresenter() {
        presenter.attachView(this);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void updateWordCount(String text) {
        wordCount.setText(text);
    }

    @Override
    public void initInput(String text) {
        input.setText(text);
    }
}
