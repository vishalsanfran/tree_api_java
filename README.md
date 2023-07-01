## A Spring Boot service that manages and manipulates trees.

Implements REST APIs with Spring Boot, Jackson, Gradle

This is how a tree looks like:

```
1: pets
    2: cats
    3: dogs
        4: pomerian
        5: poodle
    6: birds
```
I
Every node has a label and a numeric id. Indentation shows a parent <-> child relationship. So, `1: pets` has the children `2: cats, 3: dogs, 6: birds`.

&nbsp;
## API Details

For the first two tasks, persisting data in memory or simple files is adequate.

The service runs on this base url: `http://localhost:3001/api/`
<br><br>

---
<br>

### 1. `GET /api/tree` returns the entire tree in the following format;

```
[
    {
        "<id>": {
            "label": "<first item>",
            "children": [
                {
                    "<id>": {
                        "label": "<another item>",
                        "children": [] // empty children
                    }
                },
                {
                    "<id>": {
                        "label": "<another item>",
                        "children": [ ...<any children>... ]
                    }
                }
            ]
        }
    }
]
```


<br><br>


### 2. `POST /api/tree/` with the payload of the format

```
{
    "parent": "<id>",
    "label": "<label>"
}
```

Cause a node to be added to the end of a list of children, a new unique id is assigned by the service.

How it works:
A root is added when the parent_id is null.
When a parent_id is not null, a search is performed to find the corresponding node with id == parent_id. When such a node is found, we 
attach a new node with the given label as a child of the found node.

The APIs should be thread safe as non blocking concurrent collections are used to keep the state.

Assumptions:

* A tree can have more than one root. 

* Note that the POST calls are not idempotent. So, multiple calls with the
same parent_id, label will result in duplicate children.

* The POST api's response returns the newly created node object with its unique id.


---

<br>

Assuming Gradle and JDK 11 is installed:
To build and launch the app: `./gradlew bootRun`

To test the app `./gradlew clean test`

### 3. Data persistance

This is not implemented yet. But, here is an idea:

A simple hierarchial schema with an id, label and parent_id can be used.


CREATE TABLE Tree (
    id BIGINT NOT NULL AUTO_INCREMENT,
    label varchar(255) NOT NULL,
    parent_id BIGINT,
    PRIMARY KEY (id),
    INDEX(parent)
);


Sample Queries:

ET /api/tree
SELECT id, label, parent_id FROM Tree ORDER BY parent_id;
The ordering in this query should keep the siblings together. 
The client can traverse these rows and find the corrseponding the parent node and then build the tree. 
For highly skewed trees, this can be inefficient. 

A recursive CTE can order rows by level.
This will ensure a level order traversal which is faster since you look at the current level to find the parent_id.

WITH cte AS (
    SELECT id, label, parent_id FROM Tree WHERE parent_id IS NULL
        UNION ALL
    SELECT inner.id, inner.label, inner.parent_id FROM Tree inner
        INNER JOIN cte outer ON outer.id = inner.parent_id
)
SELECT * FROM cte;



POST /api/tree

A simple insert statement.

INSERT INTO Tree VALUES({body.label}, {body.parent_id});
