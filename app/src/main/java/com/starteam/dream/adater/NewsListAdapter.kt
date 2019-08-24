package com.starteam.dream.adater

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.starteam.dream.R
import com.starteam.dream.entity.NewsInfo
import com.starteam.dream.utils.GlideUtils

class NewsListAdapter : BaseQuickAdapter<NewsInfo, BaseViewHolder>(R.layout.item_text_news, listOf()) {

    companion object {

        //掘金网
        var newsInfoList1 = listOf(
            NewsInfo("https://user-gold-cdn.xitu.io/2017/10/19/25b820b93ed33f4991ee61f3290352b8?imageView2/1/w/180/h/180/q/85/format/webp/interlace/1",
                "Android_开发者", "谷歌中国 Android 开发者官方账号，汇集 Android 开发者相关的最新资讯。欢迎关注谷歌开发者官方微信公众号（Google_Developers），了解更多实时资讯。", "https://juejin.im/user/59e847cd6fb9a0451c3980d1"),
            NewsInfo("https://user-gold-cdn.xitu.io/2019/5/28/16afe0a2e8276190?imageView2/1/w/180/h/180/q/85/format/webp/interlace/1",
                "恋猫de小郭", "你们的喜欢，是我分享的动力。Android、Flutter、React Native、Weex、Cordova等欢迎大家交流。https://github.com/CarGuo", "https://juejin.im/user/582aca2ba22b9d006b59ae68"),
            NewsInfo("https://user-gold-cdn.xitu.io/2019/4/13/16a145ebc036d461?imageView2/1/w/180/h/180/q/85/format/webp/interlace/1",
                "Carson_Ho", "稀土掘金专栏作者 CSDN博客专家 简书专题作者 微信公众号：carson带你解析Android", "https://juejin.im/user/58d4d9781b69e6006ba65edc"),
            NewsInfo("https://mirror-gold-cdn.xitu.io/168e097931ce93e1a29?imageView2/1/w/180/h/180/q/85/format/webp/interlace/1",
                "JasonWuuu", "上班划水，下班写代码。", "https://juejin.im/user/5c204d5ce51d4548ac6f63c8"),
            NewsInfo("https://mirror-gold-cdn.xitu.io/168e082e8aa4c63c207?imageView2/1/w/180/h/180/q/85/format/webp/interlace/1",
                "blankj", "Android开发 ByteDance", "https://juejin.im/user/579f5dd1a341310063f9f105"),
            NewsInfo("https://user-gold-cdn.xitu.io/2018/3/2/161e5b02a8d2bbbe?imageView2/1/w/180/h/180/q/85/format/webp/interlace/1",
            "有赞技术", "有赞技术团队是一群聪明、皮实、有要性的年轻人，技术总人数近 1000 人，大多数毕业于 985、211 等中国最前线重点院校，来自 BAT 等大厂的核心人员不胜枚举，过去 5 年，团队人数从不到 10 人发展到现在将近 1000 人，内部已成长出了一批核心骨干人员。这里没有乱七八糟的勾心斗角，提倡直接有效的沟通。坐标：杭州，北京，广州。 有赞技术团队不光内部实现了很多适合高并发、大数据量、复杂业务的各种解决方同时也对外开源了一系列的解决方案（https://github.com/youzan)，我们也在技术博客/公众号（有赞coder）沉淀了很多关于有赞技术细节的文章，技术博客在未推广的情况下阅读量超过 100w 人次", "https://juejin.im/user/5a99007bf265da239c7accca")
        )
        //微信
        var newsInfoList2 = listOf(
            NewsInfo("https://img01.sogoucdn.com/app/a/100520090/oIWsFt0T1J_TqfKambF6E7gg6AW0","郭霖","Android技术分享平台,每个工作日都有优质技术文章推送.你还可以向公众号投稿,将自己总结的技术心得分享给大家.","https://weixin.sogou.com/weixin?type=1&s_from=input&query=%E9%83%AD%E9%9C%96&ie=utf8&_sug_=n&_sug_type_="),
            NewsInfo("https://img01.sogoucdn.com/app/a/100520090/oIWsFt_90TdIqCyhWxkfiW_c2yTg","Android编程精选","分享Android相关技术文章、学习资料、视频教程、热点资讯、工具资源、课程书籍等.每天推送,欢迎投稿!","https://weixin.sogou.com/weixin?type=1&s_from=input&query=Android&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=3493&sst0=1566575405884&lkt=1%2C1566575405780%2C1566575405780"),
            NewsInfo("https://img01.sogoucdn.com/app/a/100520090/oIWsFtxUPGGO07qTRlbycJr7GOAE","Android开发中文站","这里有资讯,干货,技术,源码,精彩内容不容错过!","https://weixin.sogou.com/weixin?type=1&s_from=input&query=Android&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=3493&sst0=1566575405884&lkt=1%2C1566575405780%2C1566575405780"),
            NewsInfo("https://img01.sogoucdn.com/app/a/100520090/oIWsFt6ORjvngE3kA8dhnq3Y_1zA","Android技术之家","主要分享移动互联网的相关产品和资讯、Android相关技术文章.关注你将学习到更多,在互联网的当下你会赚更多的钱","https://weixin.sogou.com/weixin?type=1&s_from=input&query=Android&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=3493&sst0=1566575405884&lkt=1%2C1566575405780%2C1566575405780"),
            NewsInfo("https://img01.sogoucdn.com/app/a/100520090/oIWsFt3edP-UBtBFbS1JgCLnqQ4k","安卓巴士Android开发者门户","专业的移动开发者门户,提供Android开发学习资料,分享有价值的互联网信息.","https://weixin.sogou.com/weixin?type=1&s_from=input&query=Android&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=3493&sst0=1566575405884&lkt=1%2C1566575405780%2C1566575405780"),
            NewsInfo("https://img01.sogoucdn.com/app/a/100520090/oIWsFt_o4rxwnaqRIdB53awqPtW8","淘宝技术","淘宝技术官方账号","https://weixin.sogou.com/weixin?type=1&s_from=input&query=Android&ie=utf8&_sug_=y&_sug_type_=&w=01019900&sut=3493&sst0=1566575405884&lkt=1%2C1566575405780%2C1566575405780")
        )
        //CSDN
        var newsInfoList3 = listOf(
            NewsInfo("https://profile.csdnimg.cn/1/E/F/1_yinwenjie","CSDN官方博客","风流倜傥。","https://me.csdn.net/yinwenjie"),
            NewsInfo("https://avatar.csdn.net/B/2/F/3_weixin_43193719.jpg","学习中的编程老菜鸟"," 一个编程菜鸟的学习里程","https://blog.csdn.net/weixin_43193719"),
            NewsInfo("https://avatar.csdn.net/A/C/E/3_lou_liang.jpg","亮亮在江湖","移动端Android技术探讨","https://blog.csdn.net/lou_liang"),
            NewsInfo("https://profile.csdnimg.cn/8/C/C/1_geduo_83","门心叼龙","乘风破浪会有时，直挂云帆济沧海","https://me.csdn.net/geduo_83"),
            NewsInfo("https://avatar.csdn.net/6/7/1/3_weixin_38703938.jpg","月薪低于10k不改名","纸上得来终觉浅，绝知此事要躬行","https://blog.csdn.net/weixin_38703938"),
            NewsInfo("https://avatar.csdn.net/B/3/4/3_weixin_38008100.jpg","行走在江湖","让优秀成为一种习惯，不在安逸中生活","https://blog.csdn.net/weixin_38008100")
        )
        //知乎
        var newsInfoList4 = listOf(
            NewsInfo("https://pic2.zhimg.com/v2-7057d15512022f9df87579cd52a776f8_im.jpg","移动开发架构师","欢迎关注。我这里整理了面试资料和高级","https://www.zhihu.com/people/yi-dong-kai-fa-jia-gou-shi/activities"),
            NewsInfo("https://pic2.zhimg.com/50/v2-3c36b544c7a5a73e7372cb4b2c99b2b2_im.jpg","Android 2012","分享Android干货，面试，进阶资","https://www.zhihu.com/people/fei-yu-94-15/activities"),
            NewsInfo("https://pic4.zhimg.com/v2-2c79b8a9faf7294f26b990c1af6785d8_im.jpg","Android架构","分享你所需的Android技术干货","https://www.zhihu.com/people/le-shi-89-62/activities"),
            NewsInfo("https://pic2.zhimg.com/v2-089be4d11035803808bbbea0a54f5e14_im.jpg","Android高级开发","Android进阶","https://www.zhihu.com/people/javagao-bing-fa/activities"),
            NewsInfo("https://pic4.zhimg.com/50/8c9ca8319_im.jpg","Android 开发","Java 虚拟机，Android 4.4以后版本可选择运行在ART上）中编译和执行","https://www.zhihu.com/topic/19555634/hot"),
            NewsInfo("https://pic4.zhimg.com/50/984f05908_im.jpg","Android 应用","基于 Android 这一开放源代码操作系统上的各种技术、服务或产品的使用","https://www.zhihu.com/topic/19555453/hot")
        )
        //今日头条
        var newsInfoList5 = listOf(
            NewsInfo("http://p1.pstatp.com/medium/ff060000622f7500965e","Android架构解析","分享Android架构、进阶技术，分享互联网资讯趣事。","https://www.toutiao.com/c/user/1121093981322988/#mid=1637295819288580"),
            NewsInfo("http://p3.pstatp.com/medium/ef7000f911dbf229685","手机技术分享","收罗分享关于android手机的新消息、新技术、新亮点！","https://www.toutiao.com/c/user/5917016633/#mid=52193409255"),
            NewsInfo("http://p1.pstatp.com/medium/17820007f532643df554","开发技术分享","分享android开发技术和解决问题","https://www.toutiao.com/c/user/6251279928/#mid=1560467566630913"),
            NewsInfo("http://p3.pstatp.com/medium/9fd30001e14e5506476e","攻城狮","分享开发技巧，干货集中营。","https://www.toutiao.com/c/user/50671879315/#mid=50667964745"),
            NewsInfo("http://p1.pstatp.com/medium/ff4600000f4807530623","高级架构师","为广大同行分享一些安卓技术相关资料，欢迎广大专业人士来交流","https://www.toutiao.com/c/user/108672454244/#mid=1620186749428744"),
            NewsInfo("http://p1.pstatp.com/medium/fe8700009b340e3dff67","架构师","移动架构，NDK开发，音视频，图像识别","https://www.toutiao.com/c/user/52630657467/#mid=52630417230")
        )
        var newsInfoList = listOf(newsInfoList1, newsInfoList2, newsInfoList3, newsInfoList4, newsInfoList5)
    }

    override fun convert(helper: BaseViewHolder?, item: NewsInfo?) {
        item?.let {
            helper?.setText(R.id.tv_title, it.title)
            helper?.setText(R.id.tv_news_desc, it.author)
        }
        val iv: ImageView? = helper?.getView(R.id.iv_logo)
        iv?.let {
            GlideUtils.load(it.context, item?.img, it)
        }
    }
}