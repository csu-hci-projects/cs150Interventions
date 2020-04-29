# cs150Interventions

# Video Links
[Check Point 2](https://youtu.be/0iZWCxlxBK8)

[Check Point 3](https://youtu.be/WjorIZTZrXU)

# Latex File
[Latex File](checkpoint2.pdf)

#Description 

*  SetInactiveStudents.kt: Kotlin script that looks at all the students in the course that you want to send emails to and sets any student that has dropped the class as inactive
   + currentStudents.csv: csv file containing all the active students in the class. This file is
    referenced to set any student who dropped the class as inactive so we don’t
    send them an email.
   + GetActiveStudents: helper class that reads the csv file.
   
*  GetCampaigns.kt: Kotlin script that splits the active students into two randomly generated groups called ‘Campaigns.’  The first campaign will receive personalized emails based on their performance in different subjects. The second group will receive general emails with information on all topics.
   
*  ReadOutcomes.kt: Kotlin script that looks at the students in the personal campaign and reads in their results from a CSV file. This will later tell us which subjects will be included in their email. 
   + Outcomes.csv: csv file containing information that determines whether or not the 
   students met expectations in each subject. 
   
*  SendEmails.kt: Kotlin script that uses a helper class to build huge Json strings that can later be parsed to send out emails. The Json strings contain a ‘to’, ‘subject,’ and ‘body’ field.
   + EmailBuilder: For each student in the campaign, this class generates the email that will
    be sent to the student. If the student is in the ‘General’ campaign it generates a fixed 
   amount of links to include in the email. If the student is in the ‘Personal’ campaign it will 
   only generate links for subjects that the student did not meet expectations(their score is 
   less than the mastery score). If the student does better than mastery in every subject 
   they do not receive an email.
   + personalizedEmail.json: json string containing all data for the personal campaign
   + generalEmail.json:json string containing all data for the general campaign
   
*  unit3GeneralEmailBody.hbs: handlebar file that serves as a template for the general emails
*  unit3PersonalEmailBody.hbs: handlebar file that serves as a template for the general emails
   

