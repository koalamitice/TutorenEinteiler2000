# TutorenEinteiler2000
This tool is used to schedules exercises for the Softwaretechnik 1 course at the Karlsruhe Institute of Technology.<br/>
This project uses Gradle, to start the tool just use the gradle wrapper (execute the **gradlew run** command).

# Work Flow
After executing the gradle run command a file dialog will pop up.<br />
Use the dialog to open a file that contains a list of all tutors, all possible exercises, and which tutors could hold them.<br />
The TutorenEinteiler2000 will parse the file and automatically schedule tutors for the exercises. The output will be written in a file called *schedule.txt*, created in the same directory as the selected file.
<br />
<br />
# Input File Format
The exact format of an input file has to be the following (without the '<', '>' characters):
```
#START: AllTut:
<TutorName1>
<TutorName2>
<TutorName3>
...
#END

#START: Table:
<WeekDay>;<Time>;<RoomNr>; <TutorName1>, <TutorName5>, ...
<WeekDay>;<Time>;<RoomNr>; <TutorName1>, <TutorName5>, ... 
...
```
The list of tutors has to start with with keyword *#START: AllTut:* and end with the keyword *#END*.<br />
The table defining exercises and possible tutors for them has to start with *#START: Table:*.
It is mandatory that Tutors listed in the table are included in the tutor list.
Example:
```
#START: AllTut:
Max Mustermann
Fred der Knecht
Kek2000
#END

#START: Table:
Montag;9:00;123; Max Mustermann, Fred der Knecht
Mittwoch;9:30;111; Kek2000, Fred der Knecht
```
Only German Weekdays are supported right now.
