#!/bin/bash

echo "This is a dummy yes/no prompt."
read -p "Do you want to continue? (y/n): " response

if [[ "$response" =~ ^[Yy]$ ]]; then
    echo "You chose Yes."
elif [[ "$response" =~ ^[Nn]$ ]]; then
    echo "You chose No."
else
    echo "Invalid response. Please enter 'y' or 'n'."
fi


