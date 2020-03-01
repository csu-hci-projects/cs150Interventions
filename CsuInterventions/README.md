# CSU  Interventions

Intervention Project that tracks Personalized Student Support Actions based
on Student Learning Outcome tracking in canvas. 

This is a very rough pass at code, done in a single day period. Primary
languages are Kotlin and Java. The project can be broken up into two parts.

## Web Application - Link Tracking

**ClickThroughController** - this simply is a forwarding URL to a student
resource. The purpose for this controller is so that we can
track which student clicks on which link, and number of times. It
can't track how long they spend on the resource, just simply choosing
to interact with it. 

**InterventionController** - was struggling with email permissions, so this
simply returns a giant JSON string that can be used with handlebars to
build the email in command line utilities, admittedly, this
should just be command line utility by itself until a Course Dashboard
is made. 

## Command Line Utilities

**SetupClients + SheetsReader** Accesses google sheets that has
both outcome information and student information. 

**massEmail** Sends out the email to students using a gmail account (rams)
account of the person who initializes the send. 

