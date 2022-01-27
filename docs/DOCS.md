<div align="center">
<h1>Leather Colorizer</h1>
<strong>Change Leather Armors colors easily.</strong>
<h3>
    <a href="https://github.com/LordRazen/leather-colorizer/blob/main/README.md">Leather Colorizer</a>
    <span> | </span>
    <a href="https://github.com/LordRazen/leather-colorizer/blob/main/docs/DOCS.md">Docs</a>
    <span> | </span>
    <a href="https://github.com/LordRazen/leather-colorizer/blob/main/docs/CONTRIBUTING.md">Contributing</a>
    <span> | </span>
    <a href="https://github.com/LordRazen/leather-colorizer/blob/main/docs/CHANGELOG.md">Changelog</a>
    <span> | </span>
    <a href="https://www.spigotmc.org/resources/leather-colorizer.99462/" target="_blank">Project Page</a>
</h3>
</div>

<hr>

# DOCS

### config.yml

| Key | description                                                                                        | options          | default |
|---|----------------------------------------------------------------------------------------------------|------------------|---|
| language | Choose the language in the languages folder                                                        | [en,de,it,ru,tr] | en |
|allowDamagedItems| Allow to colorize damaged items.                                                                   |[true, false]|false|
|allowEnchantedItems| Allow to colorize enchanted items in general.                                                      |[true, false]|false|
|keepEnchantments| Keep Enchantments and RepairCosts on the colorized item. Only used if allowEnchantedItems is true. |[true, false]|false|

### Permissions

- leathercolorizer.main: Use the Leather Colorizer

### Commands

- [`/lc`|`/leathercolorizer`] - Start Leather Colorizer
- `/lc <HexColor>` - Start Leather Colorizer with a certain HEX Color

