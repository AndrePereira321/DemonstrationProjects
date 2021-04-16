# Food Rating
Food Rating est une application Android qui permet aux utilisateurs d'évaluer plusieurs places à Bruxelles qui proposent une alimentation durable.
Les utilisateurs peuvent laisser des commentaires (enregistrement locale) ou importer des images (enregistremennt en ligne) par rapport à un de ces établissements.

## Dépendences
* Android Studio 4.2 Beta 02
* Graddle 4.2 Beta 02
* Kotlin 1.4.21
* Android SDK 30

## Bugs Connus
* Après avoir chargée une image, l'utilisateur doit revenir sur le fragment Lobby et revenir sur le fragment de Detail (qui contient le slide des images) afin d'afficher l'image.
    * Si l'utilisateur fait cela pendant que l'image est en train de chargement une exception est lancée.

## Auteur
51999 - De Sousa Pereira, André Filipe - E11