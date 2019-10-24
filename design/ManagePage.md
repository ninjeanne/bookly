# BOOKLY - Software Requirements Specification
## Use-Case Specification: Manage Page | Version 0.1

## 1. Use-Case: Manage Page

### 1.1 Brief Description

This use case describes the creation, reading, updating and deleting of a book page (CRUD).

## 2. Flow of Events

TBD

### 2.1 Basic flow

In general a user will create a page and list all inserted data of this specific page. 
One will maybe edit/update it later and from time to time one will delete it.

### 2.2 Creation  

The creation of a new page. The owner of the book has the opportunity to add new pages to his
book. Later this option will lead to a link sharing. For now the adding option will automatically
lead to the new page (a link with the userid and a uuid for this page).

TBD photo

### 2.3 Read

The user wants to be able to view all of his pages (TBD See book list). In this user case we reduce
the functionality to the view of only one page including a photo, labels and text.
TBD photo

### 2.4 Edit

During editing the user can modify his labels and text but also change the picture.

TBD photo

### 2.5 Delete

By 
TBD photo

## 3. Special Requirements

### 3.1 Owning An Account
        
In order to create a new journal the user has to have an account. Only if he has one, the dialog of a creation of a journal will be visible.

## 4. Preconditions

### 4.1 The user has to be logged in

To ensure proper privacy of journals the user has to be logged in when working with journals.

## 5. Postconditions

### 5.1 Create

After creating the new journal the user will be redirected to the list overview, where the new entry will already be displayed

### 5.2 Edit

After the user saved his edits, the updated data will be displayed in the list overview.

### 5.3 Delete

After confirming the deletion modal, the journal will be permanently removed and no longer displayed in the list overview.

## 6. Function Points

To calulate the function points for a specific use case we used the [TINY TOOLS FP Calculator](http://groups.umd.umich.edu/cis/course.des/cis525/js/f00/harvey/FP_Calc.html).

    Score:      29,92 Function Points. 
    Time spent: 596min.

![domain table](_dct.PNG)

![complexity table](_cat.PNG)
