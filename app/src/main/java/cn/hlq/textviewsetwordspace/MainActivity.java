package cn.hlq.textviewsetwordspace;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LetterSpacingTextView tv = (LetterSpacingTextView) findViewById(R.id.id_textView);
        tv.setSpacing(15);
        tv.setText("我是高人产物：一个人，只要坚持不懈，就能在别人失败的地方获得成功。对于那些深思熟虑稳步向前的人，道路并不漫长;对于那些卧薪尝胆坚韧不拔的人，荣誉并不遥远。");
    }
}
