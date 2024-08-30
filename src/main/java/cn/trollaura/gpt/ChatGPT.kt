package cn.trollaura.gpt

import com.plexpt.chatgpt.ChatGPT
import com.plexpt.chatgpt.entity.chat.ChatCompletion
import com.plexpt.chatgpt.entity.chat.Message
import snw.jkook.event.EventHandler
import snw.jkook.event.Listener
import snw.jkook.event.channel.ChannelMessageEvent
import snw.jkook.plugin.Plugin


/**
@author tRollaURa_
@since 2024/8/30
 */
class ChatGPT(val plugin: Plugin): Listener {
 val chatGPT = ChatGPT.builder()
  .apiKey(plugin.config.getString("ApiKey"))
  .apiHost(plugin.config.getString("ReverseProxy"))
  .build()
  .init()

 val messages = mutableListOf<Message>(Message.ofSystem(plugin.config.getString("IntroductionForGPT")))


 @EventHandler
 fun message(event: ChannelMessageEvent) {
  if(event.message.component.toString().startsWith("(met)${plugin.core.user.id}(met)")) {
   val message = event.message.component.toString().removePrefix("(met)${plugin.core.user.id}(met)")

   if(message.contains("新对话")) {
    messages.clear()
    messages.add(Message.ofSystem(plugin.config.getString("IntroductionForGPT")))
   }

   val chatCompletion = ChatCompletion.builder()
    .model(ChatCompletion.Model.GPT4oMini)
    .messages(messages)
    .maxTokens(3000)
    .temperature(0.9)
    .build()
   messages.add(Message.of(message))
   val response = chatGPT.chatCompletion(chatCompletion)
   println("响应中 ${event.message.sender.name}(${event.channel.name}):${event.message.component.toString().removePrefix("(met)${plugin.core.user.id}(met)")}")


   val msg = response.choices.first().message.content
   println("成功: $msg")
   event.message.reply(msg)
   messages.add(Message.ofAssistant(msg))
   messages.add(Message.ofSystem(plugin.config.getString("IntroductionTwice")))
  }
 }
}