# Game-Of-Life-LJ-DR



TASK:
- (DONE) pravidla(hladanie susedov)
- (DONE) vytvorenie GridTemp
- (DONE) krokovanie behu programu cez step 
- (DONE)threding / beh v case(neskor) -> vytvorenie slidera a napojenie to na thread
- (DONE)Prerobit menu na buttony / pripadne klavesove skratky
- prerobit feel a look gridu na faru menej tahajucu oci/ kazdych 5 odriadkovat
- dat Scroll Pannel na GridComponent
  (DONE) nastavit nepridavanie veci ak je pusteny start
- // neskor dokoncit bonusove ulohy

- layuot vylepsit
	- 5x riadkov bola hurba ciara
	- biele pozadie
	- scroll na velkost okna
	- umiestnenie layout
	- buttony po preusporiadavat
- button
	- na isty pocet 
- status panel
		- kolka generacia
		- pocet zivych buniek
		- step time

- (DONE)drag right click = vymazavanie (left = kreslenie)



CHANGELOG:
- 08/09 - dokoncena mys rightclick, zatvorenie issue pri RulesForLifeCycle, zakazanie tlacidiel pri behu programu 
- 09/01 - vyhodene menu. Vytvoreny ControlFrame, pridane buttony a slider, pridane akcie k nim, zakomentovane testy, spraveny beh programu 
- 08/31 - vytvorene funkcne krokovanie + pravidla (nemenitelne, zatial)
- 08/30 - vytvorene menu clear doplnene o step, formatovnie, zmenene pristupove identifikatory pri gridComponent, odstranene nepouzite metody 
- 08/30 - mergnuty grid s mysou, spravene menu(funkcne iba exit)
- 08/29 - grid - pridany grid objektov LifeSquare a pomocne metody pre vykreslovanie zistovanie pozicie
- 08/28	- changed "isAlive" boolean to "alive" (potential naming conflict with isAlive())
- 08/27 - mys - pridany package mouse do test. V triede Mouse Component su vyriesene eventy pre mys (David) 

