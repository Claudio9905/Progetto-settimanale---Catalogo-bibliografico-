# Progetto settimanale ( Catalogo bibliografico)

Benvenuto ad Epi-Books
Progetto creato tramite l'uso del JPA con creazione del database (PostgSQL).
In questo progetto è possibilie visualizzare un catalogo diviso tra libri e riviste tutte poi caricate in un database.
Ed è possibile fare varie operazione con esso come aggiungere elementi, rimuovere o eseguire ricerche.
Il diagramma ER è allegato tramite immagine
Il diagramma l'ho impostato in questa maniera:
ENTITA: 
- CATALOGO BIBLIOGRAFICO con le sue sotto-entità LIBRI e RIVISTE con cardinalità 1a1 (in JPA ho usato la strategia JOINED), poi esso è in relazione OnetoOne con l'entità PRESTITO 
- L'entità UTENTE è in relazione OnetoMany con l'entità PRESTITO
