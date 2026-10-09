# TP1Veterinaire

Depuis la racine du projet

## Compiler le projet :

### Commons :
```javac -d common/out  $(find common/src -name "*.java")```

### Client :
```javac -cp common/out -d client/out $(find client/src -name "*.java")```

### Serveur :
```javac -cp common/out -d server/out $(find server/src -name "*.java")```

## Lancer le projet :

### Server :
```java -cp common/out:server/out animal.server.Server --embedded```

### Client :
```java -cp common/out:client/out animal.client.Client```