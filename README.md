## M06-UF2

Aquesta activitat gestiona una base de dades per poder mantenir una bbdd de un joc, en aquest cas, VALORANT.

Gestiona dades relacionades amb rols, personatges, equips i partides en una base de dades mitjançant Hibernate. A través d'una interfície de consola, l'usuari pot realitzar operacions CRUD (Crear, Llegir, Actualitzar i Eliminar) sobre les taules de la base de dades. A més, ofereix funcionalitats per llistar tots els registres i fer consultes agregades mitjançant "GROUP BY".

El programa utilitza Hibernate per a la gestió de sessions i transaccions amb la base de dades, garantint una manipulació eficient i segura de les dades.

## Requeriments

* Totes les entitats amb els seus corresponents DAOs.
* El main de l'aplicació hauria de fer de forma mínima:
- Un menú on es faci triar a l'usuari les 4 accions més les dues requerides per la part 3 amb l'HQL a una sola (i sols una) de les entitats.
- Una setena i vuitena opció on una de les entitats persisteix l'altra i a l'inrevès. Cal que siguin entitats diferents de l'entitat emprada a les 6 primeres opcions.

## Descripció dels mètodes
### Comprovacions dins de cadascun

#### main(String[] args)

- Punt d'entrada del programa. Inicia la sessió de Hibernate, presenta un menú d'opcions i executa l'operació seleccionada per l'usuari.
Codi:

![alt text](imatges/image2.png)

- Gestiona possibles excepcions relacionades amb Hibernate i altres possibles errors inesperats.
Codi:

![alt text](imatges/image3.png)

##### MenuOptions(BufferedReader br):

- Mostra el menú principal amb opcions per inserir, llegir, actualitzar, eliminar, llistar i agregar dades.

![alt text](imatges/image1.png)

- Retorna l'opció triada per l'usuari i es fa mitjançant un switch.

![alt text](imatges/image4.png)

#####  demanarTaula(BufferedReader br)

Demana a l'usuari la taula sobre la qual vol operar (Rol, Personatge, Equip o Partida).
Aquesta opcio es fa en totes les opcions CRUD, findAll i GROUP BY.

![alt text](imatges/image5.png)

##### dadesRol(BufferedReader br, SessionFactory sessio)

Demana dades per crear un nou rol juntament amb els seus personatges associats.
Crea una instància de "Rol" i "Personatge" i els emmagatzema a la base de dades.


##### dadesPersonatge(BufferedReader br, SessionFactory sessio)

Permet la creació d'un nou personatge i la seva associació amb un rol i un equip.

Persisteix les dades a la base de dades.

##### dadesEquip(BufferedReader br, SessionFactory sessio)

Demana informació per crear un nou equip i assignar-li un personatge.

Desa l'equip a la base de dades.

##### dadesPartida(BufferedReader br, SessionFactory sessio)

Demana l'ID d'un equip i l'assigna a una nova partida.

Registra la partida a la base de dades.

##### demanarId()

Demana a l'usuari un ID per fer operacions de consulta o modificació.

##### readRol(BufferedReader br, SessionFactory sessio)

Demana un ID i recupera les dades d'un rol des de la base de dades.

##### readPersonatge(BufferedReader br, SessionFactory sessio)

Demana un ID i recupera la informació d'un personatge de la base de dades.

##### readEquip(BufferedReader br, SessionFactory sessio)

Recupera les dades d'un equip utilitzant un ID proporcionat per l'usuari.

##### readPartida(BufferedReader br, SessionFactory sessio)

Recupera les dades d'una partida segons l'ID introduït.

##### updateRol(BufferedReader br, SessionFactory sessio)

Permet actualitzar el nom d'un rol existent a la base de dades.

##### updatePersonatge(BufferedReader br, SessionFactory sessio)

Permet modificar el nom o l'ID de rol d'un personatge.

##### deleteRol(BufferedReader br, SessionFactory sessio)

Elimina un rol de la base de dades segons l'ID introduït.

##### findAllRol(BufferedReader br, SessionFactory sessio)

Llista tots els rols emmagatzemats a la base de dades.

##### agregacionsRol(BufferedReader br, SessionFactory sessio)

Permet fer operacions d'agregació (GROUP BY) a la taula de rols, agrupant per ID o nom.

##### agregacionsPersonatge(BufferedReader br, SessionFactory sessio)

Realitza operacions d'agregació a la taula de personatges, agrupant per ID, ID de rol o nom del personatge.