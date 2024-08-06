' Créez un objet WScript.Shell pour exécuter des commandes
Set objShell = CreateObject("WScript.Shell")

' Obtenez le répertoire du script VBS
scriptPath = CreateObject("Scripting.FileSystemObject").GetParentFolderName(WScript.ScriptFullName)

' Définissez les chemins complets vers les fichiers batch
batchFile2 = scriptPath & "\Exe2.bat"
batchFile1 = scriptPath & "\Exe.bat"

' Exécutez le premier fichier batch et attendez sa fin  et 1 pour afficher la console
objShell.Run batchFile2, 0, true

' Exécutez le deuxième fichier batch après que le premier soit terminé
objShell.Run batchFile1, 0, true

