# OpenSpigotChat


## Config.yml
```yaml
# Plugin by https://fazziclay.ru/
# Use https://minecraftjson.com to generate Minecraft JSONComponent.

# Команды которые нужно удалить с сервера.
unregisterCommands:
  - seed
  - pl
  - plugins
  - version
  - ver

# Сообщения о выходе и входе
message:
  enable: true
  joinPlayer: '["",{"text":"%player_nickname%","color":"yellow","clickEvent":{"action":"suggest_command","value":"/tell %player_nickname%"},"hoverEvent":{"action":"show_text","contents":"Click to send DM"}},{"text":" join to server!","color":"yellow"}]'
  leavePlayer: '{"text":"%player_nickname% leave from server!","color":"yellow"}'

# Личные сообщения
directMessage:
  enable: true
  commands:
    - tell
    - msg
    - w

  senderPattern: '{"text":"Message Sent!","color":"dark_green","clickEvent":{"action":"suggest_command","value":"/tell %recipient_nickname% "},"hoverEvent":{"action":"show_text","contents":[{"text":"~~~~ PrivateMessage ~~~~","color":"blue"},"%current_time%","\n","%sender_nickname% ",{"text":"->","color":"gold"},{"text":" %recipient_nickname%","color":"green"},"\n",{"text":"(Click to reply)","color":"light_purple"},"\n","\n",{"text":"%message_content%","color":"#FFFFFF"}]}}'
  recipientPattern: '{"text":"%sender_nickname% -> %recipient_nickname%: %message_content%","color":"dark_green","clickEvent":{"action":"suggest_command","value":"/tell %sender_nickname% "},"hoverEvent":{"action":"show_text","contents":[{"text":"~~~~ PrivateMessage ~~~~","color":"blue"},"%current_time%","\n","%sender_nickname% ",{"text":"->","color":"gold"},{"text":" %recipient_nickname%","color":"green"},"\n",{"text":"(Click to reply)","color":"light_purple"},"\n","\n",{"text":"%message_content%","color":"#FFFFFF"}]}}'
  playerNotFound: '{"translate":"argument.entity.notfound.player","color":"red"}'

  argumentsName: ["recipient", "message"]

chat:
  list: [example]
  default: example

  example:
    range: 50
    prefix: none
    chatBubbles:
      enable: true
      prefix: "[Example] "
      format: "&a&l"
      maxWidth: 20
      maxHeidth: 5
    messageType: chat
    pattern: '[{"translate":"chat.type.text","with":[{"text":"%player_nickname%","clickEvent":{"action":"suggest_command","value":"/tell %player_nickname% "},"hoverEvent":{"action":"show_text","contents":["Click to send a private message"]}},"%message_content%"]}]'

version: 0
```