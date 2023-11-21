# TaskManagement System

### Example inputs:

1. Create USER:

```
createuser Glucimir
```

2. Create TEAM:

```
createteam Otbor4eto
```

3. Create BOARD:

```
createboard D1ska
```

4. Create FEEDBACK:

```
createfeedback Obratnavruzka primerendescription 5
```

5. Add USER to a TEAM:

```
addusertoteam Glucimir Otbor4eto
```

6. Assign TASK to a USER:

```
assigntasktouser 1 Glucimir
```

7. Change PRIORITY of TASK:

```
changepriority 1 medium
```

8. Change STATUS of TASK:

```
changestatus 1 done
```

9. Change SIZE of TASK:

```
changesize 1 medium
```

10. Change SEVERITY of TASK:

```
changeseverity 1 major
```

11. Change RATING of TASK:

```
changerating 1 5
```

## WOMBO COMBO 1-7:

### Example input:

```
createuser Glucimir
createteam Otbor4eto
createboard D1ska
createfeedback Obratnavruzka primerendescription 5
assigntasktouser 1 Glucimir
addusertoteam Glucimir Otbor4eto
exit


```

### Expected output:

```
User with name Glucimir was created.
####################
Team with name Otbor4eto was created.
####################
Invalid number of arguments. Expected: 2; received: 1.
####################
Feedback with ID 1 was created.
####################
Task with ID 1 added to user Glucimir successfully.
####################
User Glucimir added to team Otbor4eto successfully.
####################
```

### Example input:

```
createuser Petar
createteam Otbor
createboard Duska Otbor
createbug Bug4etoetegavoo Bugavoedostaa Petar low minor 1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata Duska
showteamboards Otbor
```
