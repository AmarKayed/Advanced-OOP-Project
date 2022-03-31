On the deletion of an open account, the IBAN taken by that account will NOT be
redistributed to another account that is ought to be opened soon, nor will the
accounts that follow the deleted account in the sorted map be shifted in order
to close the gap between the accounts.

A person's id number will not be reassigned to another person upon deletion.

When openning an account you can specify an initial balance

The Person.id and Account.IBAN attributes CANNOT BE CHANGED => they're final

When we have temporary Person objects, we shall redistribute their ID number after they serve their purpose, since their only purpose will be to help create a real usable Person object.

When deleting a customer, all the accounts opened in his name will be also closed.