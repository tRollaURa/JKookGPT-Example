package cn.trollaura.gpt;

import snw.jkook.plugin.BasePlugin;


/**
 * @author tRollaURa_
 * @since 2024/5/19
 */
public class Main extends BasePlugin {


    @Override
    public void onLoad() {

        saveDefaultConfig();

        reloadConfig();
    }

    @Override
    public void onEnable() {
        getCore().getEventManager().registerHandlers(this,new ChatGPT(this));
        getCore().getHttpAPI().setListening("cloudmusic","tRollaURa_","@本人来和我聊天吧!");
    }

}
