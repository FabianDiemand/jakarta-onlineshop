# Dokumentation: JEE Onlineshop
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
  - [2.2 Java EE 9](#vt_javaee)
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
- [6 Architekturentscheidungen](#ae_architekturentscheidungen)
- [7 Installation](#i_installation)
  - [7.1 Container: jak-onlineshop-database](#i_dbcontainer)  
  - [7.2 Container: jak-onlineshop-webapp](#i_webappcontainer)  
  - [7.3 Run-Konfiguration](#i_runconfig)
  - [7.4 Setup-Anleitung](#i_setup)
- [Quellen](#quellen)
- [Statusberichte](#statusberichte)

---

<a name="el_einleitung"></a>
## 1 Einleitung
Das folgende Dokument enthält die Dokumentation der Semesterarbeit im Modul JEE (Java Enterprise Edition),
des Herbstsemesters 2022/23 an der Fernfachhochschule Schweiz (nachfolgend FFHS).
Im Kern geht es dabei um die Erweiterung des Onlineshops aus der Literatur zum Unterricht (Java EE 8, Alexander Salvanos).

<a name="el_abgrenzung"></a>
### 1.1 Abgrenzung
Im Rahmen der Semesterarbeit wird sich vordergründig auf die Implementation eines Kundenprofils zum Onlineshop konzentriert.
Dabei wird das Hauptaugenmerk auf die serverseitigen Funktionen gelegt. Die Klienten-Seite wird nur in einem für eine Demo nötigen Umfang
umgesetzt. 

<a name="el_glossar"></a>
### 1.2 Glossar
| Begriff    | Erklärung                                                                                      |
|------------|------------------------------------------------------------------------------------------------|
| Kundschaft | Personen, die einen Kauf beabsichtigen, sich dabei registrieren oder bereits registriert sind. |
| Besuchende | Personen, die einen Kauf beabsichtigen, jedoch noch nicht registriert sind.                    |
---

<a name="vt_verwendetetechnologien"></a>
## 2 Verwendete Technologien
Der Onlineshop soll eine Web-Applikation sein. Zu deren Umsetzung werden folgende Technologien verwendet.

<a name="vt_intellij"></a>
### 2.1 IntelliJ IDEA
Als Entwicklungsumgebung für die Java EE Web-Applikation dient IntelliJ IDEA von Jetbrains.

<a name="vt_javaee"></a>
### 2.2 Java EE 9
Gemäss der verwendeten Literatur soll für die Web-Applikation Java als Programmiersprache, in der Spezifikation EE 9,
verwendet werden.

<a name="vt_glassfish"></a>
### 2.3 Oracle Glassfish Server 6.2.5
Als Applikationsserver wird der Glassfish Open-Source-Anwendungsserver von Oracle in der Version 6.2.5 verwendet.
Glassfish ist als Anwendungsserver für Java EE konzipiert und daher speziell für dieses Vorhaben geeignet.

<a name="vt_postgresql"></a>
### 2.4 PostgreSQL 15
Das objektrelationale Datenbankmanagementsystem (ORDBMS) PostgreSQL wird genutzt, um die Datenpersistenz zu gewährleisten.

<a name="vt_gitlab"></a>
### 2.5 GitLab
Für die Source-Code-Verwaltung und die Versionierung wird GitLab verwendet. Innerhalb von Gitlab werden insbesondere Issues verwendet,
um User Stories und Tasks zu erfassen. Zu Planungszwecken wird ausserdem ein Board mit den Phasen "Backlog", "Sprint Backlog", "Development",
"Verification" und "Done" erstellt.

Die Branch-Strategie folgt grundsätzlich den Empfehlungen des Git-flow-Workflow (vgl. [GitFlow]).

<a name="vt_docker"></a>
### 2.6 Docker  
Für das Deployment bzw. die Abgabe der Applikation wird eine Container-Gruppe mit Docker Compose zur Verfügung gestellt.


<a name="vt_spezifikation"></a>
### 2.7 Spezifikation (Drawio, Figma)
Für sämtliche Modelle und Diagramme wurde [Drawio](https://www.diagrams.net/) verwendet.
Der UI- und Bedienungsprototyp wurde mit [Figma](https://www.figma.com/de) erstellt.

---

<a name="fa_funktionaleanforderungen"></a>
## 3 Funktionale Anforderungen
Die funktionalen Anforderungen werden in der Form von User Stories erfasst. Jede User Story enthält eine Kurzbeschreibung, 
eine längere technischere Beschreibung und eine Definition-of-Done (DoD) mit Akzeptanzkriterien. Ausserdem enthalten einige
User Stories eine Definition-of-Ready, wenn entsprechende Abhängigkeiten bestehen.

Für die Umsetzung werden alle User Stories in Tasks aufgebrochen, welche wie die User Stories selbst als Issues im entsprechenden
GitLab Repository erfasst sind. Im Rahmen der Dokumentation wird nur auf die User Stories eingegangen. Der Übersicht halber werden
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
      Akteure erhalten einen Überblick über die zur Verfügung stehenden Produkte mit den Optionen diese in einem Warenkorb oder einer Favoritenliste abzulegen.
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
    <th>Auslösendes Ereignis</th>
    <td>Akteure besuchen den Onlineshop.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Akteure sehen alle zur Verfügung stehenden Produkte.</td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteure besuchen den Onlineshop<br>
      2. Akteure werden direkt auf die Produktübersicht geroutet<br>
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
    <td>Akteure können Produkte in einen Warenkorb legen, die sie später kaufen möchten.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Im Shop stehen Produkte zum Kauf zur Verfügung.</td>
  </tr>
  <tr>
    <th>Auslösendes Ereignis</th>
    <td>Akteur will Produkt kaufen.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Produkte die gekauft werden sollen liegen im Warenkorb.</td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      1a. Produkte, die im Warenkorb liegen, werden mit ausgegrautem Knopf für die Ablage dargestellt.
      2a. Kaufknopf steht erst ab mindestens einem Produkt im Warenkorb zur Verfügung.
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
    <th>Auslösendes Ereignis</th>
    <td>Kundschaft klickt im Warenkorb auf die Schaltfläche, um die abgelegten Produkte zu kaufen.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Waren wurden bestellt.</td>
  </tr>
  <tr>
    <th>Normalfall</th>
    <td>
      1. Akteur klickt auf die Schaltfläche zum Kauf <br>
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
      Besuchende des Onlineshops sollen sich registrieren können, um persönliche Informationen abzulegen, 
      Produkte kaufen und in einer Wunschliste erfassen zu können.
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
    <th>Auslösendes Ereignis</th>
    <td>Besuchende klicken den "Registrieren"-Knopf im Onlineshop.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>
      Akteure sind als registrierte Kundschaft mit eigenem Profil, Authentifikationsdaten und persönlichen Informationen erfasst.
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
      2. Akteure tippen ihre persönlichen Informationen Formularfeldern ein <br>
      3. Akteure legen eine E-Mail Adresse fest <br>
      3. Akteure legen ein Passwort fest und bestätigen dieses <br>
      4. Akteure klicken den "Registrieren"-Knopf <br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>
      2a. Persönliche Informationen werden von der Kundschaft nicht ergänzt. <br>
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
      überprüfen.    
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
    <th>Auslösendes Ereignis</th>
    <td>Registrierte Kundschaft klickt den "Login"-Knopf im Onlineshop.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Registrierte Kundschaft ist eingeloggt und hat Zugang zu ihrem Profil, sowie einer Übersicht der Bestellungen und einer Wunschliste.</td>
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
      4. Akteur bestätigt das Login <br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td> 
      1a./2a. E-Mail oder Passwort stimmen nicht überein <br> 
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
    <td>Akteure sollen ihre Informationen einsehen und bearbeiten können, um ihr Profil auf dem neusten Stand zu halten.</td>
  </tr>
  <tr>
    <th>Akteure</th>
    <td>Registrierte Kundschaft</td>
  </tr>
  <tr>
    <th>Vorbedingung</th>
    <td>Akteure verfügen bereits über ein Profil mit persönlichen Informationen und sind angemeldet .</td>
  </tr>
  <tr>
    <th>Auslösendes Ereignis</th>
    <td>Akteure klicken das Profil-Icon auf einer beliebigen Ansicht des Onlineshops.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>
      Persönliche Informationen der Akteure sind aktualisiert.
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
      1. Akteure sehen auf einer Übersicht alle persönlichen Informationen im Profil <br>
      2. Akteure klicken "Information bearbeiten" <br>
      3. Akteure sehen eine Formular-Ansicht, die die Bearbeitung der Daten erlaubt <br>
      4. Akteure klicken "Profil aktualisieren", um die Änderungen zu persistieren <br>
    </td>
  </tr>
  <tr>
    <th>Sonderfall</th>
    <td>
      4a. Eingaben der Akteure entsprechen nicht den festgelegten Validitätskriterien <br>
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
    <td>Akteure können Produkte in einer Merkliste ablegen, um schneller auf favorisierte Produkte zugreifen zu können.</td>
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
    <th>Auslösendes Ereignis</th>
    <td>Akteure klicken auf das "Merkliste"-Icon eines Produkts.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>
      Akteur sieht eine Übersicht aller favorisierten Produkte.
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
    <td>Akteure erhalten eine Übersicht über getätigte Bestellungen und deren Status.</td>
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
    <th>Auslösendes Ereignis</th>
    <td>Akteure klicken das "Bestellungen"-Icon auf der Profilübersicht.</td>
  </tr>
  <tr>
    <th>Nachbedingung Normalfall</th>
    <td>Akteure können ihre Bestellungen einsehen.</td>
  </tr>
  <tr>
    <th>Nachbedingung Sonderfall</th>
    <td>
      Akteure werden über einen textlichen Output über die leere Bestellhistorie informiert.
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
Das gesamte Datenmodell enthält Kunden (mit Adressen & Wohnorten, vgl. 4.1), Produkte (vgl. 4.2),
Bestellungen (vgl. 4.3) und einer Merkliste (vgl. 4.4).
<img  src="img/erd/erd_onlineshop.jpg" alt="Datenmodell für den gesamten Onlineshop.">


<a name="dm_customer"></a>
## 4.1 Customer
Die Tabelle für die Kundendaten enthält persönliche Informationen über die Kundschaft, sowie die Authentifikationsdaten (E-Mail, Passwort).
Die Einzigartigkeit jedes Kunden wird über die E-Mail-Adresse sichergestellt. Die Customer-Entitäten werden versioniert, um die Aktualität
der Daten in der Applikation sicherzustellen.

Die Daten zur Adresse der Kunden sind in eine eigene Tabelle ausgelagert, um den Ansprüchen der Normalisierung zu entsprechen.
Bei der Adresstabelle sind aus demselben Grund wiederum ausgelagert. Beide Tabellen haben ein Versionsattribut.
<img src="img/erd/erd_customer.jpg" alt="ERD für die Customer Entity.">

<a name="dm_product"></a>
## 4.2 Product
Die Tabelle für Produktdaten enthält alle notwendigen Daten über ein Produkt, wie Name, Beschreibung und Preis. Daneben auch 
ein Attribut für den Fremdschlüssel des Verkäufers und den Fremdschlüssel eines potenziellen Käufers.
Speziell an der Produkttabelle ist das Image Attribut, das eine Binär-Repräsentation eines Produktbilds enthält. 
Wie alle anderen Entitäten sind auch die Produkt-Entitäten versioniert.<br>
<img src="img/erd/erd_product.jpg" alt="ERD für die Product Entity.">

<a name="dm_order"></a>
## 4.3 Order
Bestellungen werden mithilfe einer Tabelle für die Informationen über eine Bestellung und einer Hilfstabelle für die Many-to-Many
Beziehung zwischen Produkten und Bestellungen abgebildet.
Die Many-to-Many Beziehung wäre in dieser Form für den aktuellen Stand des Onlineshops nicht nötig. Im Sinne der Erweiterbarkeit
hat sich der Entwickler dazu entschieden, die Relation dennoch als M-to-M und nicht als M-to-1 zu modellieren.
Auf die Bestellungs-Entitäten wird nach der Erstellung nur noch lesend zugegriffen. Daher entfällt hier die Versionierung. <br>
<img src="img/erd/erd_order.jpg" alt="ERD für die Order Entity.">

<a name="dm_wishlist"></a>
## 4.4 Wishlist
Die Wishlist-Tabelle ist eine einfache Hilfstabelle für die Many-to-Many Beziehung zwischen Kunden und Produkten in Form einer Merkliste.
Zu der Merkliste sind auch keine weiteren Informationen notwendig, weshalb eine eigene Tabelle (vgl. 4.3 Orders) entfällt.<br>
<img src="img/erd/erd_wishlist.jpg" alt="ERD für die Wishlist Entity.">

<a name="ui_prototyp"></a>
## 5 UI & Bedienungsprototyp



<a name="ae_architekturentscheidungen"></a>
## 6 Architekturentscheidungen

<a name="i_installation"></a>
## 7 Installation
Für eine unkomplizierte Installation, ohne den Server selbst konfigurieren zu müssen, wird auf Docker gesetzt.
Das [docker-compose.yml](../docker/docker-compose.yml) im Verzeichnis 'jakarta-onlineshop/docker' setzt eine 
Container Group _**'jak-onlineshop-congrp'**_ bestehend aus zwei Containern _**'jak-onlineshop-database'**_ und _**'jak-onlineshop-webapp'**_ auf.
Die beiden Container kommunizieren über ein bridge-Netzwerk namens _**'jak-onlineshop-network'**_.
Der ganze Prozess wird in einer Run-Konfiguration zusammengehalten.

<a name="i_dbcontainer"></a>
### 7.1 Container: jak-onlineshop-database
Der Container basiert auf dem aktuellsten PostgreSQL Image. Darin werden ein User (onlineshop_user), mit Passwort (shop_12345) und eine Datenbank (jak_onlineshop)
aufgesetzt. Mit diesen Credentials kann z.B. über das Database Widget in IntelliJ über _host.docker.internal:5432/jak_onlineshop_ eine Verbindung zu der Datenbank
hergestellt werden.

<a name="i_webappcontainer"></a>
### 7.2 Container: jak-onlineshop-webapp
Das Base-Image für den Container ist ein um eine Glassfish Installation und den PostgreSQL JDBC Driver erweitertes jdk11 Image, das vom 
Autor veröffentlicht wurde und somit von Dockerhub bezogen werden kann. Dadurch entfällt die Konfiguration des Servers gänzlich.
Das war-File der Applikation wird über einen Mount direkt ins autodeploy Verzeichnis des Glassfish Servers im Container kopiert.

<a name="i_runconfig"></a>
### 7.3 Run-Konfiguration
Die Run-Konfiguration builded zunächst das Projekt, sodass unter 'build/lib/tech2go.war' die aktuellste Version zur Verfügung steht.
Danach werden die beiden Container mit den bereits erwähnten Konfigurationen aufgesetzt und gestartet.

<a name="i_setup"></a>
### 7.4 Setup-Anleitung
Die Anleitung geht davon aus, dass der Anwender den aktuellsten Release im Main-Branch des [Repositories](https://git.ffhs.ch/fabian.diemand/jakarta-onlineshop/-/tree/main) gecloned 
und Docker bzw. Docker Desktop installiert hat.

1. Starte Docker Desktop
2. In IntelliJ: Öffne die Run-Konfiguration 'Jakarta Onlineshop.run.xml' im 'jakarta-onlineshop/.run'-Verzeichnis
3. In IntelliJ: Klicke rechts im blauen Banner 'Open/ Debug Configurations...'
4. In IntelliJ: In der folgenden Übersicht der Run-Konfiguration muss 'Docker' als Server noch spezifiziert werden
5. In IntelliJ: Lasse die Konfiguration mit einem Klick auf das grüne Dreieck neben der Run-Konfiguration laufen
6. Im Browser: Besuche die URL [localhost:8080/tech2go](http://localhost:8080/tech2go/)


<a name="quellen"></a>
## Quellen
- [Salvanos]: _Salvanos, Alexander (2018): Professionell entwickeln mit Java EE 8: Das umfassende Handbuch. Aktuell zu Jakarta EE, 2. Aufl., Rheinwerk Computing._
- [GitFlow]: _Atlassian (o. D.): Git-flow-Workflow | Atlassian Git Tutorial, Atlassian, [online] https://www.atlassian.com/de/git/tutorials/comparing-workflows/gitflow-workflow [abgerufen am 27.10.2022]._

<a name="statusberichte"></a>
## Statusberichte

### Statusbericht 1: 28.09.2022
| Teilbereiche                      | Status [%] | Bemerkungen                                                                                                                                                      |
|-----------------------------------|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Spezifikation                     | 80         | - Festlegung Fokusbereich "Nutzerprofil"<br/>-User Stories und Tasks in Gitlab<br/>-Entsprechendes Board in Gitlab<br/>-Verwendete Technologien in Dokumentation |
| Entwurf                           | 20         | - Datenmodellierung angefangen in Draw.io<br/>- Oberflächenprototypen angefangen in Figma                                                                        |
| Programmierung                    | 0          |                                                                                                                                                                  |
| Validierung                       | 0          |                                                                                                                                                                  |
| Präsentation, Dokumentation, Demo | 5          | - Dokument erstellt, Titel und Gliederung festgelegt<br/>- Verwendete Technologien festgelegt und begründet                                                      |

### Statusbericht 2: 26.10.2022
| Teilbereiche                      | Status [%] | Bemerkungen                                                                   |
|-----------------------------------|------------|-------------------------------------------------------------------------------|
| Spezifikation                     | 100        | - User Stories und Tasks vollständig                                          |
| Entwurf                           | 60         | - Datenmodellierung abgeschlossen<br/>- Oberflächenprototypen fortgeschritten |
| Programmierung                    | 0          |                                                                               |
| Validierung                       | 0          |                                                                               |
| Präsentation, Dokumentation, Demo | 30         | - Spezifikation und Modellierung dokumentiert                                 |

<br>

### Vorlage Statusbericht <#>: <Datum>
| Teilbereiche                      | Status [%] | Bemerkungen |
|-----------------------------------|------------|-------------|
| Spezifikation                     |            |             |
| Entwurf                           |            |             |
| Programmierung                    |            |             |
| Validierung                       |            |             |
| Präsentation, Dokumentation, Demo |            |             |
