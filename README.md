# projet_remplacement_alternance

## Premier projet

Voici le sujet proposé : faire un serveur socketIO en java, compatible avec spring, correspondant à la dernière version de socketIO. Il existe des implémentations anciennes (par exemple celle utilisée en L3), mais qui ne correspond pas à la dernière version du protocole. 
Pour l'essayer, vous pourrez faire avec des clients js, puis vous pourrez faire une implémentation du client java (idem, correspondant aux dernières versions de socketIO). 
Dans les attendus : 
1. une étude des implémentations de java de websocket (client, serveur) et de socketIO, leur compatibilité avec les versions récentes (js) de socketIO et l'intégration dans différents programmes java (avec ou sans framework, avec ou sans serveur web). 
2. une réalisation (en repartant de zéro, en faisant un fork d'un existant : cela dépendra de la réponse à 1. ) . On attend que vous suivez une approche professionnelle (par exemple agile) avec une preuve de concept (par exemple, remplacer la version utiliser de socketio dans vos projets de L3). 

## Ré-orientation du projet

Je vous propose une réorientation pour le début du travail de remplacement (et on verra ensuite) : de vous concentrer pour l'instant sur l'étude de communication java (back-end ou micro service) - js (front end ou back end) (avec ou sans framework) : ajax / fetch / websocket / socketio / requete dans un framework / etc.. Accompagnez cela d'un petit exemple (du code). Et proposez une analyse pour comparer les solutions.

# Guide d'utilisation

Le serveur HTTP REST est éxécuté sur le port 8080.

Le serveur web est éxécuté sur le port 8080.

Le serveur websocket est éxécuté sur le port 8080 avec le namespace /websocket.

Le serveur socket.io est éxécuté sur le port 8082 avec le namespace /socketio.

## Éxécuter le projet

```sh
git clone https://github.com/Gilles-Marco/projet_remplacement_alternance.git
cd comparcom
mvn clean package
java -jar target/comparcom-1.0.0.jar
```

## Pour rebuild le frontend

```sh
cd comparcom_frontapp
npm install
npm run build
cp -R dist/* ../comparcom/src/main/resources/static/

cd ../comparcom
mvn clean package
java -jar target/comparcom-1.0.0.jar
```

Le build du frontend est intégré dans springboot une fois qu'on build le projet spring boot, le .jar produit aura incorporé les fichiers webs et les distribura sur l'adresse sur laquelle il s'éxécute.

Le site web est disponible sur http://localhost:8080/

# Dépendance du projet

## Javascript

- Vue
- Vue/CLI
- socket.io-client@1.7.4
- sockjs-client
- stomp-websocket

On utilise socket.io-client en version 1.7.4 malgré qu'elle date d'il y a 4 ans et que la version actuelle (2021-07-13) est là 4.1.3 car netty-socketio n'est compatible qu'avec les versions de socket.io-client 1.X.

## Java

- SpringBoot
- SpringBoot websocket
- netty-socketio

# Difficultés rencontrées

- Aucune difficulté rencontré pour les requêtes AJAX et FETCH.

- Pour les websockets nous avons rencontré une difficulté, SpringBoot Websocket utilise un autre protocole par-dessus les websockets, il utilise le protocole STOMP qui a causé l'ajout d'une dépendance au client javascript.
De plus pour les websockets lors de la réponse du serveur au client, le serveur émet un broadcast sur le topic plutôt que de répondre uniquement au client. C'est un des problèmes de websocket c'est que l'implémentation d'une authentification d'utilisateurs et de leur émettre une réponse qu'à eux est plus difficile qu'avec socket.io.

- Pour socket.io la difficulté que nous avons rencontré était pour faire communiquer le serveur java et le client javascript. En effet nous utilisions la dernière version de socket.io-client sur le client javascript (4.1.3) et nos tentatives de connexions au serveur ne fonctionnait pas. Nous n'avions pas de log produit sur le serveur, là où on voyait dans le debugueur du browser dans l'onglet network que le client javascript tentait de se connecter au serveur. En consultant la doc de netty-socketio nous avons vu qu'il ne supportait que les versions 1.0+ de socket.io-client. Nous avons donc adapté notre code à la version antérieure de socket.io-client et après cela, socket.io fonctionnait.
Nous avons aussi rencontré une autre difficulté pour socket.io, comme il y avait déjà le serveur websocket qui était éxécuté sur le port 8080 de la machine, on ne pouvait pas lancer socket.io sur le même port que websocket même sur un autre namespace. C'est pour cela qu'il s'éxécute sur le port 8082.