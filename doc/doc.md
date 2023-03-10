# Dokumentation: Jakarta Onlineshop
HS 2022, Java Enterprise Edition, Fabian Diemand <br>
Dozent: Daniel Senften <br>
Repository: https://git.ffhs.ch/fabian.diemand/jee-onlineshop/ <br>

---

## Inhalt
- [1 Einleitung](#el_einleitung)
  - [1.1 Abgrenzung](#el_abgrenzung)
  - [1.2 Glossar](#el_glossar)
- [2 Verwendete Technologien](#vt_verwendetetechnologien)
  - [2.1 IntelliJ IDEA](#vt_intellij)
  - [2.2 Jakarta EE 9](#vt_jakartaee)
  - [2.3 Glassfish Server](#vt_glassfish)
  - [2.4 PostgreSQL](#vt_postgresql)
  - [2.5 GitLab](#vt_gitlab)
  - [2.6 Docker](#vt_docker)
  - [2.7 Spezifikation (Drawio, Figma)](#vt_spezifikation)
- [3 Funktionale Anforderungen](#fa_funktionaleanforderungen)
  - [3.1 Shop - Product Overview](#fa_productoverview)
  - [3.2 Shop - Product Cart](#fa_productcart)
  - [3.3 Shop - Buy Product](#fa_buyproduct)
  - [3.4 Customer Management - Registration](#fa_registration)
  - [3.5 Customer Management - Sign In](#fa_signin)
  - [3.6 Customer Profile - Customer Info](#fa_customerinfo)
  - [3.7 Customer Profile - Wishlist](#fa_wishlist)
  - [3.8 Customer Profile - Order History](#fa_orderhistory)
- [4 Datenmodell](#dm_datenmodell)
  - [4.1 Customer](#dm_customer)
  - [4.2 Product](#dm_product)
  - [4.3 Order](#dm_order)
  - [4.4 Wishlist](#dm_wishlist)
- [5 UI & Bedienungsprototyp](#ui_prototyp)
  - [5.1 Welcome Page](#ui_welcome)
  - [5.2 Login Page](#ui_login)
  - [5.3 Register Page](#ui_register)
  - [5.4 Profile Info Page](#ui_profile_info)
  - [5.5 Profile Wishlist Page](#ui_profile_wishlist)
  - [5.6 Cart Page](#ui_cart)
  - [5.7 Orders Page](#ui_profile_orders)
- [6 Architekturentscheidungen](#ae_architekturentscheidungen)
  - [6.1 Sequenzdiagramme](#ae_sequenzdiagramme)
    - [6.1.1 Registrierung](#ae_seq_registration)
    - [6.1.2 Login](#ae_seq_login)
    - [6.1.3 Profil-Update](#ae_seq_profile_update)
    - [6.1.4 Warenkorb](#ae_seq_cart)
    - [6.1.5 Merkliste](#ae_seq_wishlist)
    - [6.1.6 Bestellungen](#ae_seq_orders)
- [7 Installation](#i_installation)
  - [7.1 Container: jak-onlineshop-database](#i_dbcontainer)  
  - [7.2 Container: jak-onlineshop-webapp](#i_webappcontainer)  
  - [7.3 Run-Konfiguration](#i_runconfig)
  - [7.4 Setup-Anleitung](#i_setup)
  - [7.5 Demo-Anwendung](#i_demo)
- [Quellen](#quellen)
- [Statusberichte](#statusberichte)

---

<a name="el_einleitung"></a>
## 1 Einleitung
Das folgende Dokument enth??lt die Dokumentation der Semesterarbeit im Modul JEE (Java Enterprise Edition),
des Herbstsemesters 2022/23 an der Fernfachhochschule Schweiz (nachfolgend FFHS).
Im Kern geht es dabei um die Erweiterung des Onlineshops aus der Literatur zum Unterricht (Java EE 8, Alexander Salvanos).

<a name="el_abgrenzung"></a>
### 1.1 Abgrenzung
Im Rahmen der Semesterarbeit wird sich vordergr??ndig auf die Implementation eines Kundenprofils zum Onlineshop konzentriert.
Dabei wird das Hauptaugenmerk auf die serverseitigen Funktionen gelegt. Die Klienten-Seite wird nur in einem f??r eine Demo n??tigen Umfang
umgesetzt. 

<a name="el_glossar"></a>
### 1.2 Glossar
| Begriff    | Erkl??rung                                                                                      |
|------------|------------------------------------------------------------------------------------------------|
| Kundschaft | Personen, die einen Kauf beabsichtigen, sich dabei registrieren oder bereits registriert sind. |
| Besuchende | Personen, die einen Kauf beabsichtigen, jedoch noch nicht registriert sind.                    |
---

<a name="vt_verwendetetechnologien"></a>
## 2 Verwendete Technologien
Der Onlineshop soll eine Web-Applikation sein. Zu deren Umsetzung werden folgende Technologien verwendet.

<a name="vt_intellij"></a>
### 2.1 IntelliJ IDEA
Als Entwicklungsumgebung f??r die Java EE Web-Applikation dient IntelliJ IDEA von Jetbrains.

<a name="vt_jakartaee"></a>
### 2.2 Jakarta EE 9
Gem??ss der verwendeten Literatur soll f??r die Web-Applikation Java als Programmiersprache, in der Spezifikation EE 9,
verwendet werden.

<a name="vt_glassfish"></a>
### 2.3 Oracle Glassfish Server 6.2.5
Als Applikationsserver wird der Glassfish Open-Source-Anwendungsserver von Oracle in der Version 6.2.5 verwendet.
Glassfish ist als Anwendungsserver f??r Java EE konzipiert und daher speziell f??r dieses Vorhaben geeignet.

<a name="vt_postgresql"></a>
### 2.4 PostgreSQL 15
Das objektrelationale Datenbankmanagementsystem (ORDBMS) PostgreSQL wird genutzt, um die Datenpersistenz zu gew??hrleisten.

<a name="vt_gitlab"></a>
### 2.5 GitLab
F??r die Source-Code-Verwaltung und die Versionierung wird GitLab verwendet. Innerhalb von Gitlab werden insbesondere Issues verwendet,
um User Stories und Tasks zu erfassen. Zu Planungszwecken wird ausserdem ein Board mit den Phasen "Backlog", "Sprint Backlog", "Development",
"Verification" und "Done" erstellt.

Die Branch-Strategie folgt grunds??tzlich den Empfehlungen des Git-flow-Workflow (vgl. [GitFlow]).

<a name="vt_docker"></a>
### 2.6 Docker  
F??r das Deployment bzw. die Abgabe der Applikation wird eine Container-Gruppe mit Docker Compose zur Verf??gung gestellt.


<a name="vt_spezifikation"></a>
### 2.7 Spezifikation (Drawio, Figma)
F??r s??mtliche Modelle und Diagramme wurde [Drawio](https://www.diagrams.net/) verwendet.
Der UI- und Bedienungsprototyp wurde mit [Figma](https://www.figma.com/de) erstellt.

---

<a name="fa_funktionaleanforderungen"></a>
## 3 Funktionale Anforderungen
Die funktionalen Anforderungen werden in der Form von User Stories erfasst. Jede User Story enth??lt eine Kurzbeschreibung, 
eine l??ngere technischere Beschreibung und eine Definition-of-Done (DoD) mit Akzeptanzkriterien. Ausserdem enthalten einige
User Stories eine Definition-of-Ready, wenn entsprechende Abh??ngigkeiten bestehen.

F??r die Umsetzung werden alle User Stories in Tasks aufgebrochen, welche wie die User Stories selbst als Issues im entsprechenden
GitLab Repository erfasst sind. Im Rahmen der Dokumentation wird nur auf die User Stories eingegangen. Der ??bersicht halber werden
diese in Use-Case-Form erfasst.

<a name="fa_productoverview"></a>
### 3.1 Shop - Product Overview
<table>
  <tr>
    <th>Name</th>
    <td>Shop - Product Overview</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>
      Akteure erhalten einen ??berblick ??ber die zur Verf??gung stehenden Produkte mit den Optionen diese in einem Warenkorb oder einer Favoritenliste abzulegen.
    </td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>-</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Akteure besuchen den Onlineshop.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Akteure sehen alle zur Verf??gung stehenden Produkte.</td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteure besuchen den Onlineshop<br>
      2. Akteure werden direkt auf die Produkt??bersicht geroutet<br>
    </td>
  </tr>
</table>

<a name="fa_productcart"></a>
### 3.2 Shop - Product Cart
<table>
  <tr>
    <th>Name</th>
    <td>Shop - Product Cart</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>Akteure k??nnen Produkte in einen Warenkorb legen, die sie sp??ter kaufen m??chten.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Im Shop stehen Produkte zum Kauf zur Verf??gung.</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Akteur will Produkt kaufen.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Produkte die gekauft werden sollen liegen im Warenkorb.</td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      1a. Produkte, die im Warenkorb liegen, werden mit ausgegrautem Knopf f??r die Ablage dargestellt.
      2a. Kaufknopf steht erst ab mindestens einem Produkt im Warenkorb zur Verf??gung.
    </td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteur klickt auf den Knopf, der ein Produkt in den Warenkorb legt<br>
      2. Akteur klickt im Warenkorb auf den Knopf, um die abgelegten Produkte zu kaufen<br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>
      1a. Akteur will Produkt in den Warenkorb legen, das bereits in diesem liegt.<br>
      2a. Akteur will den Kaufknopf eines leeren Warenkorbs klicken <br>
    </td>
  </tr>
</table>

<a name="fa_buyproduct"></a>
### 3.3 Shop - Buy Product
<table>
  <tr>
    <th>Name</th>
    <td>Shop - Buy Product</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>Registrierte Kundschaft kann Produkte kaufen, welche zuvor im Warenkorb abgelegt wurden.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Zu kaufende Produkte wurden im Warenkorb abgelegt.</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Kundschaft klickt im Warenkorb auf die Schaltfl??che, um die abgelegten Produkte zu kaufen.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Waren wurden bestellt.</td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteur klickt auf die Schaltfl??che zum Kauf <br>
      2. Produkte sind im Profil unter Bestellungen zu finden <br>
    </td>
  </tr>
</table>

<a name="fa_registration"></a>
### 3.4 Customer Management - Registration
<table>
  <tr>
    <th>Name</th>
    <td>Customer Management - Registration</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>
      Besuchende des Onlineshops sollen sich registrieren k??nnen, um pers??nliche Informationen abzulegen, 
      Produkte kaufen und in einer Wunschliste erfassen zu k??nnen.
    </td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Besuchende</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>-</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Besuchende klicken den "Registrieren"-Knopf im Onlineshop.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>
      Akteure sind als registrierte Kundschaft mit eigenem Profil, Authentifikationsdaten und pers??nlichen Informationen erfasst.
    </td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      2a. Formular kann nicht abgeschlossen werden, solange noch Informationen fehlen <br>
      3a. Akteur wird darauf aufmerksam gemacht, dass die E-Mail Adresse bereits vergeben ist <br>
    </td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteure klicken auf das Login-Icon in der Nav-Bar & anschliessend auf den "Registrieren"-Link <br>
      2. Akteure tippen ihre pers??nlichen Informationen Formularfeldern ein <br>
      3. Akteure legen eine E-Mail Adresse fest <br>
      3. Akteure legen ein Passwort fest und best??tigen dieses <br>
      4. Akteure klicken den "Registrieren"-Knopf <br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>
      2a. Pers??nliche Informationen werden von der Kundschaft nicht erg??nzt. <br>
      3a. E-Mail-Adresse ist bereits im Kundensystem erfasst. <br>
    </td>
  </tr>
</table>

<a name="fa_signin"></a>
### 3.5 Customer Management - Sign In
<table>
  <tr>
    <th>Name</th>
    <td>Customer Management - Sign In</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>
      Registrierte Kundschaft kann sich in deren Profil einloggen, um Daten zu modifizieren, Wunschliste und Bestellstatus zu
      ??berpr??fen.    
    </td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Registrierte Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Akteure sind bereits registrierte Kundschaft.</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Registrierte Kundschaft klickt den "Login"-Knopf im Onlineshop.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Registrierte Kundschaft ist eingeloggt und hat Zugang zu ihrem Profil, sowie einer ??bersicht der Bestellungen und einer Wunschliste.</td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      1a./2a. Akteure bekommen eine diskrete Fehlermeldung angezeigt.
    </td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteur wird zur Eingabe der E-Mail aufgefordert <br>
      2. Akteur wird zur Eingabe des Passworts aufgefordert <br>
      4. Akteur best??tigt das Login <br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td> 
      1a./2a. E-Mail oder Passwort stimmen nicht ??berein <br> 
    </td>
  </tr>
</table>

<a name="fa_customerinfo"></a>
### 3.6 Customer Profile - Customer Info
<table>
  <tr>
    <th>Name</th>
    <td>Customer Profile - Customer Info</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>Akteure sollen ihre Informationen einsehen und bearbeiten k??nnen, um ihr Profil auf dem neusten Stand zu halten.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Registrierte Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Akteure verf??gen bereits ??ber ein Profil mit pers??nlichen Informationen und sind angemeldet .</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Akteure klicken das Profil-Icon auf einer beliebigen Ansicht des Onlineshops.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>
      Pers??nliche Informationen der Akteure sind aktualisiert.
    </td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      4a. Prozess kann nur abgeschlossen werden, wenn alle Informationen valide sind. Akteure werden auf invalide Eingaben hingewiesen.
    </td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteure sehen auf einer ??bersicht alle pers??nlichen Informationen im Profil <br>
      2. Akteure klicken "Information bearbeiten" <br>
      3. Akteure sehen eine Formular-Ansicht, die die Bearbeitung der Daten erlaubt <br>
      4. Akteure klicken "Profil aktualisieren", um die ??nderungen zu persistieren <br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>
      4a. Eingaben der Akteure entsprechen nicht den festgelegten Validit??tskriterien <br>
    </td>
  </tr>
</table>

<a name="fa_wishlist"></a>
### 3.7 Customer Profile - Wishlist
<table>
  <tr>
    <th>Name</th>
    <td>Customer Profile - Wishlist</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>Akteure k??nnen Produkte in einer Merkliste ablegen, um schneller auf favorisierte Produkte zugreifen zu k??nnen.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Registrierte Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Akteure sind angemeldet.</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Akteure klicken auf das "Merkliste"-Icon eines Produkts.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>
      Akteur sieht eine ??bersicht aller favorisierten Produkte.
    </td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>-</td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Produkt wird als Favorit gekennzeichnet
      2. Akteur klickt auf das "Merkliste"-Icon im Profil
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>-</td>
  </tr>
</table>

<a name="fa_orderhistory"></a>
### 3.8 Customer Profile - Order History
<table>
  <tr>
    <th>Name</th>
    <td>Customer Profile - Order History</td>
  </tr>
  <tr>
    <th>Ziel</th>
    <td>Akteure erhalten eine ??bersicht ??ber get??tigte Bestellungen und deren Status.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Registrierte Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Akteure sind angemeldet. Bestellungen sind vorhanden.</td>
  </tr>
  <tr>
    <th>Ausl??sendes Ereignis</th>
    <td>Akteure klicken das "Bestellungen"-Icon auf der Profil??bersicht.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Akteure k??nnen ihre Bestellungen einsehen.</td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      Akteure werden ??ber einen textlichen Output ??ber die leere Bestellhistorie informiert.
    </td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>-</td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>
      Keine Bestellungen vorhanden.
    </td>
  </tr>
</table>

---

<a name="dm_datenmodell"></a>
## 4 Datenmodell
Das gesamte Datenmodell enth??lt Kunden (mit Adressen & Wohnorten, vgl. 4.1), Produkte (vgl. 4.2),
Bestellungen (vgl. 4.3) und einer Merkliste (vgl. 4.4).
<img  src="img/erd/erd_onlineshop.jpg" alt="Datenmodell f??r den gesamten Onlineshop.">

<a name="dm_customer"></a>
### 4.1 Customer
Die Tabelle f??r die Kundendaten enth??lt pers??nliche Informationen ??ber die Kundschaft, sowie die Authentifikationsdaten (E-Mail, Passwort).
Die Einzigartigkeit jedes Kunden wird ??ber die E-Mail-Adresse sichergestellt. Die Customer-Entit??ten werden versioniert, um die Aktualit??t
der Daten in der Applikation sicherzustellen.

Die Daten zur Adresse der Kunden sind in eine eigene Tabelle ausgelagert, um den Anspr??chen der Normalisierung zu entsprechen.
Bei der Adresstabelle sind aus demselben Grund wiederum ausgelagert. Beide Tabellen haben ein Versionsattribut.
<img src="img/erd/erd_customer.jpg" alt="ERD f??r die Customer Entity.">

<a name="dm_product"></a>
### 4.2 Product
Die Tabelle f??r Produktdaten enth??lt alle notwendigen Daten ??ber ein Produkt, wie Name, Beschreibung und Preis. Daneben auch 
ein Attribut f??r den Fremdschl??ssel des Verk??ufers und den Fremdschl??ssel eines potenziellen K??ufers.
Speziell an der Produkttabelle ist das Image Attribut, das eine Bin??r-Repr??sentation eines Produktbilds enth??lt. 
Wie alle anderen Entit??ten sind auch die Produkt-Entit??ten versioniert.<br>
<img src="img/erd/erd_product.jpg" alt="ERD f??r die Product Entity.">

<a name="dm_order"></a>
### 4.3 Order
Bestellungen werden mithilfe einer Tabelle f??r die Informationen ??ber eine Bestellung und einer Hilfstabelle f??r die Many-to-Many
Beziehung zwischen Produkten und Bestellungen abgebildet.
Die Many-to-Many Beziehung w??re in dieser Form f??r den aktuellen Stand des Onlineshops nicht n??tig. Im Sinne der Erweiterbarkeit
hat sich der Entwickler dazu entschieden, die Relation dennoch als M-to-M und nicht als M-to-1 zu modellieren.
Auf die Bestellungs-Entit??ten wird nach der Erstellung nur noch lesend zugegriffen. Daher entf??llt hier die Versionierung. <br>
<img src="img/erd/erd_order.jpg" alt="ERD f??r die Order Entity.">

<a name="dm_wishlist"></a>
### 4.4 Wishlist
Die Wishlist-Tabelle ist eine einfache Hilfstabelle f??r die Many-to-Many Beziehung zwischen Kunden und Produkten in Form einer Merkliste.
Zu der Merkliste sind auch keine weiteren Informationen notwendig, weshalb eine eigene Tabelle (vgl. 4.3 Orders) entf??llt.<br>
<img src="img/erd/erd_wishlist.jpg" alt="ERD f??r die Wishlist Entity.">

---

<a name="ui_prototyp"></a>
## 5 UI & Bedienungsprototyp
Die UI und Bedienungsprototypen wurden im Vorgang zum eigentlichen Entwicklungsprozess erstellt, um bei der Umsetzung
nicht mehr viel Zeit in die Konzeption der Oberfl??chen stecken zu m??ssen, sondern direkt mit deren Umsetzung als Code beginnen zu k??nnen.
Wie eingangs erw??hnt, wurde f??r die Umsetzung der Prototypen [Figma](https://www.figma.com/de) verwendet. 

Letztlich kam es bei der Umsetzung zu kleineren Abweichungen, teils weil die Technologie (JSF) gewisse Dinge nicht im Rahmen
der sinnvollen Zeit erlaubte, teils weil der Entwickler sich zu praktischeren Ans??tzen entschied, als im Entwurf vorgesehen.

<a name="ui_welcome"></a>
### 5.1 Welcome Page
Besucher des Onlineshops werden direkt mit einer ??bersicht ??ber alle verf??gbaren Produkte begr??sst. Im nicht-eingeloggten Zustand, ist auf 
dieser Seite kaum etwas m??glich. Lediglich die Navigationsoptionen f??r die Homepage, f??rs Login bzw. die Registrierung und den Wechsel der Sprache
stehen zur Verf??gung. <br>
<img width=75% src="img/ui/welcome.jpg" alt="Welcome Page f??r Besucher (nicht eingeloggt)."> <br> <br>
Einmal eingeloggt, werden zus??tzlich die Navigationsoptionen f??r den Einkaufswagen und das Kundenprofil verf??gbar. Die Option f??rs Login wird
nun durch die M??glichkeit f??rs Logout ersetzt. Die Produktkarten bieten im eingeloggten Zustand die Funktionalit??t, zum Warenkorb hinzugef??gt,
oder in einer Wunschliste abgelegt zu werden. <br>
<img width=75% src="img/ui/welcome_loggedin.jpg" alt="Welcome Page f??r Kunden (eingeloggt).">

<a name="ui_login"></a>
### 5.2 Login Page
Klickt der Besucher auf der Welcome Page den Login-Knopf, kann er sich entweder einloggen, oder sich auf die Registrationsseite (vgl. 5.3)
weiterleiten lassen. Im Login-Formular m??ssen die E-Mail-Adresse und das Passwort eingetragen werden. K??nnen diese nicht verifiziert werden,
wird der Besucher mit einer Fehlernachricht darauf hingewiesen.<br>
<img width=75% src="img/ui/login.jpg" alt="Login-Seite">

<a name="ui_register"></a>
### 5.3 Register Page
Will sich der Besucher des Onlineshops nicht einloggen, sondern zuerst registrieren, l??sst er sich auf die Regirationsseite weiterleiten. 
Hier wird er dazu aufgefordert, seine pers??nlichen Informationen in einem Formular einzutragen.
Das Passwort muss best??tigt werden. Passwort und E-Mail-Adresse dienen letztlich der Authentifizierung. <br>
<img width=75% src="img/ui/register.jpg" alt="Registrationsseite.">

<a name="ui_profile_info"></a>
### 5.4 Profile Info Page
Ist ein Kunde im Onlineshop eingeloggt, hat er die M??glichkeit, seine pers??nlichen Daten zu ver??ndern. Das Formular sieht jenem der
Registrationsseite sehr ??hnlich, beinhaltet allerdings bereits die jeweiligen Daten des Kunden. Dadurch dient es der ??bersicht und 
Anpassung gleichermassen. <br>
<img width=75% src="img/ui/customer_info.jpg" alt="Pers??nliche Informationen der Kunden und die M??glichkeit, diese zu bearbeiten.">

<a name="ui_profile_wishlist"></a>
### 5.5 Profile Wishlist Page
Produkte, die auf der Hauptseite in die Merkliste verschoben wurden, werden in der Merkliste angezeigt. Die Merkliste
ist ??ber ein Drop-Down-Men?? von der Profil-Option in der Navigation aus erreichbar. Produkte sehen identisch aus, wie 
jene der Welcome Page (vgl. 5.1), k??nnen von hier aus ebenfalls in den Warenkorb verschoben, oder direkt wieder aus der
Merkliste entfernt werden. Produkte auf der Merkliste, die zwischenzeitlich verkauft wurden, werden entsprechend markiert. <br>
<img width=75% src="img/ui/wishlist.jpg" alt="Welcome Page f??r Kunden (eingeloggt).">

<a name="ui_cart"></a>
### 5.6 Cart Page
Produkte, die auf der Hauptseite in den Warenkorb verschoben wurden, werden hier angezeigt. Die Merkliste ist ??ber ein Drop-Down-Men??
von der Profil-Option in der Navigation aus erreichbar. Die Darstellung der Produkte weicht von der bisherigen Card-Darstellung ab.
Dies damit die Preise horizontal untereinander stehen und somit visuell besser mit der Darstellung des Totals vereinbar sind. Produkte 
k??nnen ??ber das Kreuz oben rechts wieder aus dem Warenkorb entfernt werden. Wird der Kaufen-Knopf geklickt, ist die Liste danach in der
Bestell??bersicht (vgl. 5.7) ersichtlich.<br>
<img width=75% src="img/ui/cart.jpg" alt="Warenkorb-Ansicht.">

<a name="ui_profile_orders"></a>
### 5.7 Orders Page
Wurde die Bestellung einer Liste von Produkten im Warenkorb ausgel??st, ist diese danach in der Bestell??bersicht auffindbar. 
Eine Bestellung enth??lt hier die ??bersicht ??ber Bezahlungsstatus, Versandstatus, Produktliste und Totalpreis der Bestellung. <br>
<img width=75% src="img/ui/orders.jpg" alt="??bersicht der Bestellungen.">

---

<a name="ae_architekturentscheidungen"></a>
## 6 Architekturentscheidungen
Die Dokumentation der Architekturentscheidungen bezieht sich nur auf den gew??hlten Fokusbereich des Kundenprofils, sowie auf 
die Funktionalit??ten der Registration und des Logins.

Die nachfolgende Darstellung zeigt den Aufbau der Applikation. Dieser Aufbau folgt einem MVC-Pattern. 

F??r die Umsetzung des Data-Layer (Model) wird die Jakarta Persistence API (JPA) verwendet. Der Datenbankzugriff wird dabei zus??tzlich durch ein Repository-Pattern abstrahiert.
Im Business-Layer (Controller; nicht verwechseln mit der Controller-Komponente des Pr??sentations-Layers!) kommen Utility-Klassen zum Einsatz, Enums und ein Pojo f??r die Warenkorb-Funktionalit??t.
Der Pr??sentations-Layer (View) setzt auf Jakarta Server Faces (JSF) und JSF-Facelets f??r die Oberfl??chengestaltung bzw. -generierung. Mit Controllern wird die Business-Logik abstrahiert.
Servlets werden f??r asynchrone Operationen (Bilder laden) eingesetzt.

<img src="img/arch/architecture.jpg" alt="Aufbau des Onlineshops.">

<a name="ae_sequenzdiagramme"></a>
### 6.1 Sequenzdiagramme
Anstelle eines komplexen, un??bersichtlichen Klassendiagramms wird die Kernfunktionalit??t in Sequenzdiagrammen aufgezeigt.

<a name="ae_seq_registration"></a>
#### 6.1.1 Registrierung
Eine wichtige Komponente bei der Registrierung ist die Validierung der eingetragenen Daten. Werden Teile der Informationen 
zu einem Kunden als nicht valide erkannt, wird dem Kunden die entsprechende Fehlermeldung in der UI angezeigt und das Formular
kann nicht abgeschlossen werden.
Mit korrekten Daten kann das Formular abgeschlossen werden. Eine weitere Pr??fung erfolgt, um sicherzustellen, dass die E-Mail-Adresse
des Kunden nicht bereits verwendet wird. Schl??gt diese Pr??fung fehl, wird auch dieser Umstand in der UI angezeigt.
Sofern die E-Mail-Adresse frei war, wird der Kunde nun in der Datenbank persistiert und eine entsprechende Erfolgsnachricht in der UI angezeigt.<br>
<img src="img/arch/seq_registration.jpg" alt="Sequenzdiagramm f??r die Registrierung eines Kunden.">

<a name="ae_seq_login"></a>
#### 6.1.2 Login
Beim Login findet keine unmittelbare Validierung der eingegebenen Werte statt. Wichtig ist jedoch, dass eine E-Mail und ein Passwort
angegeben werden. Andernfalls kann das Formular nicht abgeschlossen werden.
Mit dem Abschluss des Formulars wird versucht, einen Kunden mit der entsprechenden E-Mail-Adresse zu finden. 
Das eingegebene Passwort wird anschliessend mit dem gehashten Passwort des gefundenen Kunden abgeglichen.
Passen Passwort und Hash-Wert, wird der Kunde eingeloggt und auf die Homepage (vgl. 5.1) umgeleitet.
Schl??gt die Authentifikation fehl, wird eine entsprechende Meldung angezeigt und der Kunde bleibt ausgeloggt.<br>
<img src="img/arch/seq_login.jpg" alt="Sequenzdiagramm f??r das Login eines Kunden.">

<a name="ae_seq_profile_update"></a>
#### 6.1.3 Profil-Update
Das Update der Kundeninformationen im Kundenprofil ??hnelt stark der Registration. Der Validierungsprozess ist identisch, weshalb darauf
nicht mehr eingegangen wird.
Nun wird allerdings der Kunde am Ende nicht neu erstellt, sondern die Informationen des bestehenden Kunden ??berschrieben.<br>
<img src="img/arch/seq_profile_update.jpg" alt="Sequenzdiagramm f??r das Profilupdate eines Kunden.">

<a name="ae_seq_cart"></a>
#### 6.1.4 Warenkorb
Der Warenkorb wird nicht in der Datenbank persistiert, sondern existiert nur innerhalb der aktuellen Browser Session. 
Entsprechend ist er auch nicht als JPA-Entity im Data Layer, sondern als einfaches Pojo im Domain-Layer umgesetzt.
Die Interaktionen zum Hinzuf??gen und Entfernen von Produkten zum und aus dem Warenkorb laufen somit g??nzlich ohne ein Repository bzw. den Data Layer ab.
Eine Produktbestellung wird ausgel??st, indem die Liste der Produkte im Warenkorb dem Bestell-Controller ??bergeben wird.
Der Bestell-Controller l??st in den Repositories der Produkte und der Bestellungen die entsprechenden ??nderungen aus, 
um ein Produkt als verkauft zu markieren und eine Bestellung zu erzeugen. Zuletzt wird der Warenkorb geleert.<br>
<img src="img/arch/seq_cart.jpg" alt="Sequenzdiagramm f??r die Warenkorb-Funktion.">

<a name="ae_seq_wishlist"></a>
#### 6.1.5 Merkliste
Der Zustand der Merkliste wird persistiert. Allerdings geh??rt die Merkliste immer zu einem Kunden und ist somit als Liste direkt 
in der Kunden-Entit??t umgesetzt. Es gibt nie ein eigenes Merklisten-Objekt und entsprechend auch kein Merklisten-Repository.
S??mtliche ??nderungen der Merkliste laufen ??ber das Kunden-Repository. F??r die Darstellung der Merkliste wird diese vom Kunden im Profil-Controller abgefragt.
Das Hinzuf??gen von Produkten kann vom Index oder aus dem Warenkorb geschehen. Stellvertretend ist im Sequenzdiagramm nur der Index gelistet.
Beim Hinzuf??gen und Entfernen von Produkten wird der Profil-Controller beauftragt, welcher das Kunden-Repository die entsprechenden Updates in der 
Datenbank durchf??hren l??sst.<br>
<img src="img/arch/seq_wishlist.jpg" alt="Sequenzdiagramm f??r die Merkliste eines Kunden.">

<a name="ae_seq_orders"></a>
#### 6.1.6 Bestellungen
Die Erstellung von Bestellungen ist im Abschnitt des Warenkorbs (vgl. 6.1.4) dokumentiert.
F??r die Darstellung der Bestellungen wird der Bestellungs-Controller beim Repository die Bestellungen des Kunden abfragen und diese
dann dem Facelet f??r die Darstellung ??bergeben.<br>
<img src="img/arch/seq_orders.jpg" alt="Sequenzdiagramm f??r die Bestellungen eines Kunden.">

---

<a name="i_installation"></a>
## 7 Installation
F??r eine unkomplizierte Installation, ohne den Server selbst konfigurieren zu m??ssen, wird auf Docker gesetzt.
Das [docker-compose.yml](../docker/docker-compose.yml) im Verzeichnis 'jakarta-onlineshop/docker' setzt eine 
Container Group _**'jak-onlineshop-congrp'**_ bestehend aus zwei Containern _**'jak-onlineshop-database'**_ und _**'jak-onlineshop-webapp'**_ auf.
Die beiden Container kommunizieren ??ber ein bridge-Netzwerk namens _**'jak-onlineshop-network'**_.
Der ganze Prozess wird in einer Run-Konfiguration zusammengehalten.

<a name="i_dbcontainer"></a>
### 7.1 Container: jak-onlineshop-database
Der Container basiert auf dem aktuellsten PostgreSQL Image. Darin werden ein User (onlineshop_user), mit Passwort (shop_12345) und eine Datenbank (jak_onlineshop)
aufgesetzt. Mit diesen Credentials kann z.B. ??ber das Database Widget in IntelliJ ??ber _host.docker.internal:5432/jak_onlineshop_ eine Verbindung zu der Datenbank
hergestellt werden.
Die Datenbank wird mit einem Script beim Start des Containers entsprechend der Bed??rfnisse der Applikation aufgesetzt.

<a name="i_webappcontainer"></a>
### 7.2 Container: jak-onlineshop-webapp
Das Base-Image f??r den Container ist ein um eine Glassfish Installation und den PostgreSQL JDBC Driver erweitertes jdk11 Image, das vom 
Autor ver??ffentlicht wurde und somit von Dockerhub bezogen werden kann. Dadurch entf??llt die Konfiguration des Servers g??nzlich.
Das war-File der Applikation wird ??ber einen Mount direkt ins autodeploy Verzeichnis des Glassfish Servers im Container kopiert.

<a name="i_runconfig"></a>
### 7.3 Run-Konfiguration
Die Run-Konfiguration builded zun??chst das Projekt, sodass unter 'build/lib/tech2go.war' die aktuellste Version zur Verf??gung steht.
Danach werden die beiden Container mit den bereits erw??hnten Konfigurationen aufgesetzt und gestartet.

<a name="i_setup"></a>
### 7.4 Setup-Anleitung
Die Anleitung geht davon aus, dass der Anwender den aktuellsten Release im Main-Branch des [Repositories](https://git.ffhs.ch/fabian.diemand/jakarta-onlineshop/-/tree/main) gecloned 
und Docker bzw. Docker Desktop installiert hat.

0. Ports 5432, 8080, 8181, 4848 m??ssen frei sein; das Script 'jakarta-onlineshop/docker/db/01-init.sh' muss mit Unix-Style Line-Endings (LF) aufweisen
1. Starte Docker Desktop
2. In IntelliJ: ??ffne die Run-Konfiguration 'Jakarta Onlineshop.run.xml' im 'jakarta-onlineshop/.run'-Verzeichnis
3. In IntelliJ: Klicke rechts im blauen Banner 'Open/ Debug Configurations...'
4. In IntelliJ: In der folgenden ??bersicht der Run-Konfiguration muss evtl. 'Docker' als Server noch spezifiziert werden
5. In IntelliJ: Lasse die Konfiguration mit einem Klick auf das gr??ne Dreieck neben der Run-Konfiguration laufen
6. Im Browser: Besuche die URL [localhost:8080/tech2go](http://localhost:8080/tech2go/)


<a name="i_demo"></a>
### 7.5 Demo-Anwendung
Die Anwendung wird geseeded, da einige Funktionalit??ten nur in der Darstellung umgesetzt sind, jedoch aktuell nicht mit
der Applikation erzeugt werden k??nnen (z.B. Bezahlungs- und Bestellstatus).
Mit dem Kunden "John Doe" (E-Mail: _john@doe.com_, Passwort: _Johndoe1!_) k??nnen alle Funktionen des Onlineshops genutzt werden.
In den Bestellungen seines Profils sind alle Bestell- und Bezahlstatus einsehbar. <br>
---

<a name="quellen"></a>
## Quellen
- [Salvanos]: _Salvanos, Alexander (2018): Professionell entwickeln mit Java EE 8: Das umfassende Handbuch. Aktuell zu Jakarta EE, 2. Aufl., Rheinwerk Computing._
- [GitFlow]: _Atlassian (o.??D.): Git-flow-Workflow | Atlassian Git Tutorial, Atlassian, [online] https://www.atlassian.com/de/git/tutorials/comparing-workflows/gitflow-workflow [abgerufen am 27.10.2022]._

<a name="statusberichte"></a>
## Statusberichte

### Statusbericht 1: 28.09.2022
| Teilbereiche                      | Status [%] | Bemerkungen                                                                                                                                                      |
|-----------------------------------|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Spezifikation                     | 80         | - Festlegung Fokusbereich "Nutzerprofil"<br/>-User Stories und Tasks in Gitlab<br/>-Entsprechendes Board in Gitlab<br/>-Verwendete Technologien in Dokumentation |
| Entwurf                           | 20         | - Datenmodellierung angefangen in Draw.io<br/>- Oberfl??chenprototypen angefangen in Figma                                                                        |
| Programmierung                    | 0          |                                                                                                                                                                  |
| Validierung                       | 0          |                                                                                                                                                                  |
| Pr??sentation, Dokumentation, Demo | 5          | - Dokument erstellt, Titel und Gliederung festgelegt<br/>- Verwendete Technologien festgelegt und begr??ndet                                                      |

### Statusbericht 2: 26.10.2022
| Teilbereiche                      | Status [%] | Bemerkungen                                                                   |
|-----------------------------------|------------|-------------------------------------------------------------------------------|
| Spezifikation                     | 100        | - User Stories und Tasks vollst??ndig                                          |
| Entwurf                           | 60         | - Datenmodellierung abgeschlossen<br/>- Oberfl??chenprototypen fortgeschritten |
| Programmierung                    | 0          |                                                                               |
| Validierung                       | 0          |                                                                               |
| Pr??sentation, Dokumentation, Demo | 30         | - Spezifikation und Modellierung dokumentiert                                 |

<br>

### Statusbericht 3: 27.11.2022
| Teilbereiche                      | Status [%] | Bemerkungen                                                         |
|-----------------------------------|------------|---------------------------------------------------------------------|
| Spezifikation                     | 100        |                                                                     |
| Entwurf                           | 100        | - Diagramme abgeschlossen <br>- Oberfl??chenprototypen abgeschlossen |
| Programmierung                    | 60         | - Facelets, Datenlayer und Controller weit fortgeschritten          |
| Validierung                       | 40         | - einzelne Tests geschrieben                                        |
| Pr??sentation, Dokumentation, Demo | 40         | - Entwurf fertig dokumentiert                                       |
