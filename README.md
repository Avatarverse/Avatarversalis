# Avatarversalis
**Avatarversalis** is the name I am giving to a new bending plugin. The name can be changed later if needed, but I decided it would be nice to name it after the first server that would use it, like Orion did with his original *Minecraft: The Last Airbender* plugin. The name is a Latin construction, the adjective form of "Avatarverse," like the Latin "universalis" is to "universum." But I'm calling the plugin AVS for short.

Avatarversalis was created as a spiritual successor to "old" ProjectKorra, meaning the current plugin, before the changes in the impending rework. It is our belief that those who have been playing ProjectKorra for a long time should be able to continue playing the same game with which they fell in love, with essentially the same mechanics.

There have been other bending plugins besides ProjectKorra, but these did not satisfy my personal desires for a bending plugin for various reasons. That is why I decided to finally make my own.

## Features
Avatarversalis promises to include all of the necessary and community-desired features offered by ProjectKorra, plus more. There will be the four elements and chiblocking, with roughly the same abilities and mechanics. Our philosophy is that we will preserve mechanics that many people want, but we are willing to experiment with novel (or resurfaced) ideas that will enhance bending, rather than take away from its magic.

In addition to the above promise, AVS will follow all the practices that many expect from a modern bending plugin:

- **Decoupling the core API from the platform implementations** (making the plugin platform-agnostic, after which we can support other Minecraft platforms such as Sponge)
- **Decoupling the core systems from the abilities** (allowing developers to add/replace elements much more easily)
- **Decoupling ability descriptions from ability instances** (allowing for cleaner and more efficient design and performance)
- **A robust, flexible, clean, and well-documented API** (easier than ever for addon developers to learn how to make abilities, etc.)
- **Allowing non-player users** (easy to make bending mobs, projectiles, or even blocks)
- **Event-driven region protection**
- **Consolidated ability activation** (no need for long event handlers checking every ability individually)
- **Efficient combo activation**
- **Efficient and customizable collisions**
- **Efficient language/translation management**

But what about the actual gameplay? I plan to make AVS a bending plugin that is highly functional, sophisticated, interesting, and innovative. My original goal was to make bending gameplay the same as ProjectKorra's to maximize accessibility, but after some thought, I've decided that some aspects of the game would benefit from some changing. These changes, however, must have the support of the users, as is the way with all game development.

I vow to consult a wide cross-section of the bending community before releasing changes on the gameplay they learned and love. I welcome any and all ideas and suggestions; those should be directed to the project's [Discord server](https://discord.gg/6vGgHZeqY6).

## Progress
The plugin is in its early-to-mid stages of development. The following core systems have been completed but may need to be altered later:

- Element system and elements
- Ability/AbilityInstance system
- User system
- Combo system
- Attribute/Modifier system
- EndingPolicy system
- Particles, Sounds, and Effects utility classes
- FauxLight system
- Commands and command framework/parser
- Config system and default config
- Scoreboard
- Cooldown system

What's left:

- Collision system
- TempBlock, TempArmor, etc.
- PlaceholderAPI hook
- Region protection system
- Database stuff
- Any utility classes/methods we may need

The actual bending/gameplay mechanics are still in their brainstorming phase. That's where you come in! Drop your great ideas in the #suggestions channel in our [Discord](https://discord.gg/6vGgHZeqY6), and let's have a discussion!

## Contributing
Avatarversalis welcomes contributions of any kind. The project is still in development, so the code you see here is not set in stone. If there are problems with it, please feel free to point them out. If you would like to make a pull request, we welcome that, but please try to conform to the established code style, particularly the fluent getters/setters style. If you're interested in making some long-term contributions by joining the development team, please send me a DM on Discord. We will also be opening other positions eventually, such as Bug Testers and a Marketing Manager.