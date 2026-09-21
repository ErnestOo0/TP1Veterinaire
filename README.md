# TP1Veterinaire

## Choix implementation A3:

Le client et le serveur partagent le dossier, il ne s'agit pas d'une copie. Sela permet au client de travailler directement dessus, si on gardait le dossier pour le serveur, il faudrait le renvoyer dès que le dossier est modifié (par le client ou un autre client);
Si on avais transmis le dossier par référence, il aurait fallu faire des fonction pour le modifier à travers un autre objet.
