# USOM-Console-App
Usom Console App is a console application where you can send get request to show incidents and announcements in USOM, also post request to create email in USOM.
  
  In order to start the application run Main.kt. After running Main.kt you have 4 options to choose.
  
![image](https://user-images.githubusercontent.com/57874982/195118974-5ca6236d-438f-44b6-8739-cc5531fced52.png)


## Get Incidents
If you press 1 in main menu the application will send a GET requet to 'https://www.usom.gov.tr/api/incident/index', and you will get incidents that are published in USOM. You can also navigate between pages with pressing 1 for previous page 2 for next page. Pressing 0 will let you return to main menu.

## Get Announcements
If you press 2 in main menu the application will send a GET requet to 'https://www.usom.gov.tr/api/announcement/index', and you will get announcements that are published in USOM. You can also navigate between pages with pressing 1 for previous page 2 for next page if there are more than 1 page. Pressing 0 will let you return to main menu.

## Create Email
If you press 3 in main menu you will be asked to write an email. If you type a valid email application will send a POST request to 'https://www.usom.gov.tr/api/emails/create' with given email, otherwise it gives an error. Pressing 0 will let you return to main menu.

## Exit
If you press 4 in main menu, the application exits.
