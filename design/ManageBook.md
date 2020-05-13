# BOOKLY - Software Requirements Specification
## Use-Case Specification: Manage Book

## 1. Use-Case: Manage Book

![ManageBook](manageBook_mockup.png "Manage Book")

### 1.1 Brief Description

This use case describes the creation, reading, updating and deleting of a book(CRUD).
Since the creation and display of a book is more complex, they were defined as separate UseCases and stored 
in separate files. The activity diagram also does not show their functions in detail, but refers to their outsourced activities.

## 2. Flow of Events

For further details for reading, creating and updating pages look into their linked use case specification.
![ManageBookFlow](ManageBookFlow.jpg "Manage Book Flow")

### 2.1 Basic flow

In general a user will create a friendship book cover and enter the data of this cover page. 
You will be able edit/update it later and you are able to delete the friendship book in its entirety. 

### 2.2 Creation  

After registering a user will have his own friendship book. It's correlated with his account.

[Create Book](design_CreateBook.md "Create Book")


### 2.3 Read

A user can browse through his book. Starting with the cover, he can reach all
entries by clicking on arrows to the right or left on the bottom of the page.
The view of the cover or a page includes a photo or picture, labels and text.

[Read Book](ReadBook.md "Read Book Cover")

### 2.4 Edit

During editing the user can modify the title and subtitle of his friendship book.
Later on the user can also select a background image, a theme and further decorations. 
See [Design Manage Cover](design_Manage_Cover_Decorations.md "Design Manage Cover")

### 2.5 Delete

A friendship book shall only be deleted by the owner. This is only possible by deleting his whole account.
See [Operate Account](OperateAccount.md "Operate Account")

## 3. Special Requirements

### 3.1 Owning An Account
        
In order to create a friendship book the user has to have an account. For editing the cover, 
the user has to own the account and view the page. After clicking on the pencil, the user 
will be able to make changes to his cover page.

## 4. Preconditions

### 4.1 The user has to be logged in

To ensure proper privacy of a friendship book the user has to be logged in when working with his book.

## 5. Postconditions

### 5.1 Create

After adding data to the text fields and/or photos, the user can save 
his friendship book cover. 

### 5.3 Edit

The user can edit your book cover as often as he like. 
He only needs to be logged in. For Managing Pages see [ManagePage](ManagePage.md "Manage Page")


### 5.4 Delete

After confirming the deletion dialog of a book, the book pages will be no longer displayed in the list 
overview and all the book data will be permanently removed from the database. The book is correlated with
the user account and only deletable by removing the account. See [OperateAccount](OperateAccount.md "Operate Account")

## 6. Feature Files
[Feature Manage Book](../backend/src/test/resources/dhbw/online/bookly/ManageBook.feature)
