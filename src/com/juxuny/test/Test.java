package com.juxuny.test;

import com.juxuny.sensitive.EmptyException;
import com.juxuny.sensitive.Sensitive;

public class Test {

    public static final String text = "2018年9月，摩纳哥电信和华为在阿尔贝二世亲王访华期间正式签署合作协议 图自华为\n" +
            "去年9月，摩纳哥电信与华为签署了一项协议，计划使这个小国成为欧洲第一个5G全覆盖的国家。\n" +
            "“在摩纳哥，5G承诺为所有人提供更好的生活质量和非凡的机遇。”该国数字化转型负责人弗雷德里克•詹塔（Frederic Genta）9日表示。\n" +
            "报道指出，尽管华为在摩纳哥的业务范围很小，但对于华为副董事长郭平来说，5G在摩纳哥的推出却是一个重大机遇。\n" +
            "郭平对法新社表示，“在一些领域比如5G和智慧城市的结合上，它（摩纳哥）使得我们能够打开一扇橱窗。该国可以作为其他运营商和国家的榜样。”\n" +
            "另据中新社报道，摩纳哥电信官方网站已开始在显著位置推介5G技术，表示摩纳哥的私人和商业客户将能够在兼容5G的智能手机上使用这项新技术。官网推介的手机为支持5G技术的华为Mate 20 X型手机。";

//    public static final String text = "陈东京7";

    public static void main(String[] args) throws EmptyException {
        String[] words = {"摩纳哥", "华为"};
        Sensitive sensitive = new Sensitive();
        sensitive.addWord(words);
        sensitive.walkThrough(System.out::println);
        System.out.println("replace result: " + sensitive.replace(text, '*'));
        System.out.println("contain: " + sensitive.contain(text));
        System.out.println("find first: " + sensitive.first(text));
        System.out.println("filter: " + sensitive.filter(text));
    }
}
