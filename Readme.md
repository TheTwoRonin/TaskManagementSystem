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
createboard Duska
```

4. Create FEEDBACK:

```
createfeedback Obratnavruzka primerendescription 5 Duska
```

4. Create STORY:

```
createstory {{Istoriya Slavyanobolgarskaya}} {{Story za ig}} Petar high medium Duska
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
addusertoteam Petar Otbor
createboard Duska Otbor
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Duska
createstory {{Istoriya Slavyanobolgarskaya}} {{Story za ig}} Petar high medium Duska
createfeedback Obratnavruzka primerendescription 5 Duska
showteamboards Otbor
showboardactivity Duska
showteamactivity Otbor
```

```
createteam Otbor
createboard Stena Otbor
createuser Petar
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
createstory {{Istoriya Slavyanobolgarskaya}} {{Story za ig}} Petar high medium Stena
createfeedback Obratnavruzka primerendescription 5 Stena
addcomment 1 {{Super e}} Petar
showteamboards Otbor
listsortedtasks
```

# Show Team Members/Users/UserActivity command input:

```
createuser Petar
createuser Talpata
createuser Didakamf
createuser {{Ongo Bongo}}
createteam Otbor
createteam Teamcheto
addusertoteam Petar Otbor
addusertoteam Talpata Otbor
addusertoteam Didakamf Otbor
addusertoteam {{Ongo Bongo}} Teamcheto
showteammembers Otbor
showteams
showusers
showuseractivity Petar

```

```
createteam Otbor
createboard Stena Otbor
createuser Petar
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
createstory {{Aistoriya Slavyanobolgarskaya}} {{Story za ig}} Petar high medium Stena
createfeedback Obratnavruzka primerendescription 5 Stena
addcomment 1 {{Super e}} Petar
listsortedtasks
listfilteredtasks Obratnavruzka

createbug {{Aug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
createbug {{Dug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
createbug {{Cug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena

listsortedbugs
```

```
createteam Otbor
createboard Stena Otbor
createuser Petar
createuser Getar
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Getar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
changestatus 1 done
changestatus 2 done
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
createbug {{Bug 4e to e t e gavoo}} {{Bugavo e dostaa}} Petar low minor {{1.Otvarqsh 2.Otlepqsh bisktivite 3.Oblizvash krema ako iskash i 3 puti, no vnimavash da neti iztrupne ezika 4.Zalepvash 5.Izqjdash biskvitkata}} Stena
listfilteredbugs done
listfilteredbugs done getar
```