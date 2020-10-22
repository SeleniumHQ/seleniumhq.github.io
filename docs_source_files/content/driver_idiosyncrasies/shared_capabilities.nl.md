---
title: "Gedeelde capabilities"
weight: 1
---

### pageLoadStrategy
Bij het navigeren naar een nieuwe pagina, zal Selenium standaard wachten totdat de pagina volledig geladen is. Deze strategie werkt goed voor beginners maar kan snel resulteren in lange wachttijden op paginas die veel externe resources moeten ophalen. Het gebruik van niet-standaard laadstrategieen kan de doorlooptijd aanzienlijk versnellen maar kan eveneens flakiness introduceren doordat elementen van grootte en/of positie veranderen tijdens het laden.

The page load strategy bevraagt
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
zoals hieronder beschreven:

| Strategie | Ready State | Opmerkingen |
| -------- | ----------- | ----- |
| normal | complete | Standaard, wacht tot alle resources gedownload zijn |
| eager | interactive | DOM access is gereed, maar andere resources, zoals grafische elementen, zijn mogelijks nog niet volledig ingeladen. |
| none | Any | WebDriver houdt geen rekening met wachttijden |
