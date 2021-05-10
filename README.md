# OpenSpigotChat


## Config.yml
```yaml
# Plugin by https://fazziclay.ru/
# Use https://minecraftjson.com or https://minecraft.tools/en/json_text.php to generate Minecraft JSONComponent.

# Commands to delete
unregisterCommands:
  - seed
  - pl
  - plugins
  - version
  - ver

# Player messages
# * enable      - Whether to change standard messages
# * joinPlayer  - Message about logging in to the server
# * leavePlayer - Message about disconnecting from the server
# Available variables in joinPlayer and leavePlayer:
#   | %player_nickname% - The nickname of the player
message: 
  enable: true
  joinPlayer: '{"translate":"multiplayer.player.joined", ,"color":"yellow", "with":[{"text":"%player_nickname%","clickEvent":{"action":"suggest_command","value":"/tell %player_nickname% "},"hoverEvent":{"action":"show_text","contents":["Click to send a private message!"]}}]}'
  leavePlayer: '{"translate":"multiplayer.player.left", "color":"yellow", "with": ["%player_nickname%"]}'


# Private messages
# * enable            - Whether to change standard commands
# * commands          - Commands to change
# * senderPattern     - message to the sender
# * recipientPattern  - message to the recipient
# * playerNotFound    - message that the recipient is not online
# * argumentsName     - command argument names
# Available variables in senderPattern, recipientPattern and playerNotFound
#   | %sender_nickname%    - Nickname of the sender of the message
#   | %recipient_nickname% - Nickname of the message recipient
#   | %message_content%    - Sent message
directMessage:
  enable: true
  commands:
    - tell
    - msg
    - w

  senderPattern: '{"text":"Message sent!","color":"dark_green","clickEvent":{"action":"suggest_command","value":"/tell %recipient_nickname% "},"hoverEvent":{"action":"show_text","contents":[{"text":"---- Private Message ----","color":"dark_green"},"\n","%sender_nickname% -> %recipient_nickname%","\n","\n","%message_content%"]}}'
  recipientPattern: '{"text":"%sender_nickname% -> %recipient_nickname%: %message_content%","color":"gray","clickEvent":{"action":"suggest_command","value":"/tell %recipient_nickname% "},"hoverEvent":{"action":"show_text","contents":[{"text":"---- Private Message ----","color":"dark_green"},"\n","%sender_nickname% -> %recipient_nickname%","\n","\n","%message_content%"]}}'
  playerNotFound: '{"translate":"argument.entity.notfound.player","color":"red"}'

  argumentsName: ["recipient", "message"]


# Changing chats. By default, there is an "example" chat that
# cannot be removed from the configuration, but you can remove
# it from the server. To do this, remove it from the "list" tab
# To add a chat, copy the chat 'example' and change its name.
# To make it available on the server, add its name to the 'list' tab.
#
# * list    - Chats list available on the server
# * default - Default chat (without prefix)
# * <chat_name>.range       - Chat range. Use -1 to all server
# * <chat_name>.prefix      - The prefix of the chat to write to it. Use 'none' is this chat default
# * <chat_name>.messageType - Message type. "chat" and "system" are available. https://wiki.vg/Chat
# * <chat_name>.pattern     - The message itself sent to this chat
# Available variables in <any_chat_name>.pattern
#   | %sender_nickname% - Nickname of the sender of the message
#   | %message_content% - Sent message
chat:
  list: [example]
  default: example

  example:
    range: 50
    prefix: none
    messageType: chat
    pattern: '[{"translate":"chat.type.text","with":[{"text":"%sender_name%","clickEvent":{"action":"suggest_command","value":"/tell %sender_name% "},"hoverEvent":{"action":"show_text","contents":["Click to send a private message!"]}},"%message_content%"]}]'

version: 0
```