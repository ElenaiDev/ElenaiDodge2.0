# Elenai Dodge 2

Elenai Dodge 2 is a dodging mod that serves as an API for all my future projects

## Installation

Simply drag and drop the mod downloaded from the files section [here](https://www.curseforge.com/minecraft/mc-mods/elenai-dodge-2/files) into your mods folder after installing [Minecraft Forge](files.minecraftforge.net).
The mods folder is located at  ```%appdata%/.minecraft/mods```.

## User Information
For more information on the project, please refer to the [Wiki](https://github.com/ElenaiDev/ElenaiDodge2.0/wiki).


## Developer Information

Adding support to Elenai Dodge 2 is easy. To import it, simply add this line to your ```build.gradle``` file's ```repositories```.

```gradle
repositories {
     maven { url "https://www.cursemaven.com" }
}
```
And this to your project's dependencies.
```gradle
dependencies {
	compile fg.deobf("curse.maven:elenaidodge2-442962:FILE_VERSION") 
}
```
Where ```FILE_VERSION``` is the file ID of the version you want to use. You can find this by opening the URL of an Elenai Dodge 2 file download such as [this one](https://www.curseforge.com/minecraft/mc-mods/elenai-dodge-2/files/3215435) and looking at the url. The numbers at the end are the file ID.

### Example
```gradle
dependencies {
    minecraft 'net.minecraftforge:forge:1.16.4-35.1.37'
	compile fg.deobf("curse.maven:elenaidodge2-442962:3215435") 
}
```

Then simply run your gradle setup commands as normal, e.g. ```gradlew genEclipseRuns``` then ```gradlew eclipse```.

You now have access to Elenai Dodge 2's API and code! The API is pretty self explanatory but if you need any help, feel free to private message me [here](https://www.curseforge.com/members/elenaidev/followers)!

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License](https://creativecommons.org/licenses/by-nc-sa/3.0/)

