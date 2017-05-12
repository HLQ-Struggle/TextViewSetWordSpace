> LZ-Says：从个人的角度来说为什么要写博客，一方面可以拓展知识，通过分享去了解更多的知识；二方面可以通过优质的博文去推广自己。
> 

> 写博客，就好像工作一样，要认真，要对自己写的东西负责，更要对别人看的负责。 
> 
> 虽然目前技术不是很牛，但我相信，一点一滴积累，总会有起来的那天。
> 
> 我们一起加油~笑看工作中不顺心~ 

## 前言
说说今天遇到的问题吧。

老大说，搞个类似身份证原样的布局。其中一些TextView需要设置相对应的**字间距**，网上搜了n个，郁闷的我，真想骂娘，都是什么啊。不过，民间总有大神在，好歹解决了我的问题。下面给大家简单介绍下今天遇到的坑。

## 坑的多了，经验也就多了，莫怕

关于TextView设置**字间距**，有的人就说了，那不是so esay吗，直接一个属性搞定。

> **他们说：<font color=#FF0000>可以利用 TextView 的 setTextScaleX() 方法设置字间距**

LZ满怀信心的去验证了，嘿，还真可以。美了没一会儿，老大说，貌似不对。瞬间凌乱了，让我们一起看看这到底什么情况？
### 验证之路
首先打开一个布局，整俩个TextView，网上随便找段话复制上去，方便我们查看效果。

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_margin="15dp"
    android:orientation="vertical"
    tools:context="cn.hlq.textviewsetwordspace.MainActivity">

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:layout_marginBottom="5dp"
        android:background="#CCCCCC"/>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="我是准备测试例子：一个人，只要坚持不懈，就能在别人失败的地方获得成功。对于那些深思熟虑稳步向前的人，道路并不漫长;对于那些卧薪尝胆坚韧不拔的人，荣誉并不遥远。"/>

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:layout_marginBottom="5dp"
        android:layout_marginTop="5dp"
        android:background="#CCCCCC"/>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="我是设置textScaleX例子：一个人，只要坚持不懈，就能在别人失败的地方获得成功。对于那些深思熟虑稳步向前的人，道路并不漫长;对于那些卧薪尝胆坚韧不拔的人，荣誉并不遥远。"
        android:textScaleX="1.5"/>
</LinearLayout>

```

布局文件很简单，一个是测试的原文，一个是设置了android:textScaleX，那么接下来，看看他们分别展示的效果，到底有何区别。

![这里写图片描述](http://img.blog.csdn.net/20170511000116974?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

按照网上某些教程所述，设置这个属性即可实现我们所要效果，**but**，事实摆在我们眼前，一目了然。

按照谷歌提供方法，我们一般都可以从方法名称简介得出此方法或者属性名称代码什么含义。基于这个，LZ百度了下，如下：

![这里写图片描述](http://img.blog.csdn.net/20170511002737829?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

按照字面理解，结合效果，我们可以得出以下结论：

> **android:textScaleX="比例"：<font color=#FF0000> 即水平按照比例进行缩放** 

so 各位，这个所谓的能实现，压根的就是忽悠。那么问题来了，那么，如果原型图中，给定了某个TextView的字间距，那么这时候，我们该怎么办呢？

表急，**<font color=#FF0000>民间自有大神在！**

经过搜索，排除，Fuck之后，找到一位高手提供解决的方案 ，经过测试后，发现能如愿实现我们想要的效果。

那么，接下来，让我们看看高手是如何解决这个问题的吧~

下面引用高手说的一些话：

> I built a custom class that  **<font color=#FF0000>extends TextView and adds a method "setSpacing"</font>**. The workaround is similar to what @Noah said. The method adds a space between each letter of the String and with SpannedString changes the TextScaleX of the spaces, allowing positive and negative spacing.
> 
**<font color=#FF0000>Hope that helps someone ^^**

LZ英文不是很6，大体猜测如下：

> 创建一个类，继承**TextView**，添加一个**setSpacing**方法。这个方法在字符串中的每个字符之间添加了一个空间，并通过TextScaleX以X轴等比例进行缩放，允许其在前后字符间添加空间。
>  
>  PS：LZ英文不是很6，如有不正之处，欢迎大家指正~

接下来，我们一块瞅瞅高人是如何实现的。

### 民间自有高人在

废话不多说直接先给大家瞅瞅效果，免得说LZ坑人。

啦啦啦，德玛西亚万岁~

![这里写图片描述](http://img.blog.csdn.net/20170512003539146?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

为了给大家看的直观方便，特意设置了大一点，现在是不是很清楚的看到了我们要的效果~撸码，撸码~

```
package cn.hlq.textviewsetwordspace;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HLQ on 2017/5/12
 */
public class LetterSpacingTextView extends TextView {

    private float spacing = Spacing.NORMAL;
    private CharSequence originalText = "";

    public LetterSpacingTextView(Context context) {
        super(context);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 获取字间距
     *
     * @return
     */
    public float getSpacing() {
        return this.spacing;
    }

    /**
     * 设置间距
     *
     * @param spacing
     */
    public void setSpacing(float spacing) {
        this.spacing = spacing;
        applySpacing();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applySpacing();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    /**
     * 添加应用空间
     */
    private void applySpacing() {
        if (this == null || this.originalText == null) return;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if (i + 1 < originalText.length()) {
                // \u00A0 不间断空格
                // 追加空格
                builder.append("\u00A0");
            }
        }
        // TextView通常用来显示普通文本，但是有时候需要对其中某些文本进行样式、事件方面的设置。Android系统通过SpannableString类来对指定文本进行相关处理，具体有以下功能：
        // 1、BackgroundColorSpan 背景色
        // 2、ClickableSpan 文本可点击，有点击事件
        // 3、ForegroundColorSpan 文本颜色（前景色）
        // 4、MaskFilterSpan 修饰效果，如模糊(BlurMaskFilter)、浮雕(EmbossMaskFilter)
        // 5、MetricAffectingSpan 父类，一般不用
        // 6、RasterizerSpan 光栅效果
        // 7、StrikethroughSpan 删除线（中划线）
        // 8、SuggestionSpan 相当于占位符
        // 9、UnderlineSpan 下划线
        // 10、AbsoluteSizeSpan 绝对大小（文本字体）
        // 11、DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
        // 12、ImageSpan 图片
        // 13、RelativeSizeSpan 相对大小（文本字体）
        // 14、ReplacementSpan 父类，一般不用
        // 15、ScaleXSpan 基于x轴缩放
        // 16、StyleSpan 字体样式：粗体、斜体等
        // 17、SubscriptSpan 下标（数学公式会用到）
        // 18、SuperscriptSpan 上标（数学公式会用到）
        // 19、TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
        // 20、TypefaceSpan 文本字体
        // 21、URLSpan 文本超链接
        // 我们也是通过这个，去设置空格
        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) { // 如果当前TextView内容长度大于1，则进行空格添加
            for (int i = 1; i < builder.toString().length(); i += 2) { // 小demo：100  1 0 0
                // 按照x轴等比例进行缩放 通过我们设置的字间距+1除以10进行等比缩放
                finalText.setSpan(new ScaleXSpan((spacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, TextView.BufferType.SPANNABLE);
    }

    public class Spacing {
        public final static float NORMAL = 0;
    }
}
```

看完代码，突然貌似想明白了一些，高手也是曲线救国，不知道大家有没有注意到下面这几行代码：

```
    if (builder.toString().length() > 1) { // 如果当前TextView内容长度大于1，则进行空格添加
            for (int i = 1; i < builder.toString().length(); i += 2) { // 小demo：100  1 0 0
                // 按照x轴等比例进行缩放 通过我们设置的字间距+1除以10进行等比缩放
                finalText.setSpan(new ScaleXSpan((spacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
```

同样是通过ScaleXSpan来实现我们的要求，哈哈~

## 结束
经过这个博文，LZ明白了，有时候解决问题不一定要正面干，曲线救国也不乏是种可靠的办法~

感谢大家查阅~

下面为大家附上CSDN博文地址：

> http://blog.csdn.net/u012400885/article/details/71599497

特别喜欢CSDN的一句话，在此分享给大家，共勉~

![这里写图片描述](http://img.blog.csdn.net/20170512010347389?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjQwMDg4NQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 参考资料
**民间自有高人在！感谢高手奉献~**

下面为大家附上参考地址：
> http://stackoverflow.com/questions/1640659/how-to-adjust-text-kerning-in-android-textview/1644061#1644061 



