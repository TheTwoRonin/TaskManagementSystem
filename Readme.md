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

4.Create FEEDBACK:

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

## WOMBO COMBO 1-7:

### Example input:

```
createuser Glucimir
createteam Otbor4eto
createboard D1ska
createfeedback Obratnavruzka primerendescription 5
assigntasktouser 1 Glucimir
addusertoteam Glucimir Otbor4eto

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
```