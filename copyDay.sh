#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 <XX>"
  exit 1
fi

YEAR="year2025"
NEW_DAY="day$1"
NEW_FILE="Day$1"
OLD_DAY="day00"
OLD_FILE="Day00"
SRC_DIR="src/$YEAR"

# Ensure year2025 directory exists
mkdir -p "$SRC_DIR"

# Copy the directory
cp -r "src/$OLD_DAY" "$SRC_DIR/$NEW_DAY"

# Rename files inside the new directory
for file in "$SRC_DIR/$NEW_DAY"/*; do
  new_file=$(echo "$file" | sed "s/$OLD_FILE/$NEW_FILE/")
  mv "$file" "$new_file"
done

# Update references inside the files
for file in "$SRC_DIR/$NEW_DAY"/*; do
  # Update package declaration to use dot
  sed -i '' "s/package $OLD_DAY/package $YEAR.$NEW_DAY/g" "$file"
  # Update references to use slash
  sed -i '' "s/$OLD_DAY/$YEAR\/$NEW_DAY/g" "$file"
  sed -i '' "s/$OLD_FILE/$NEW_FILE/g" "$file"
  # Fix package references that may have been changed to use slash, revert to dot for package declaration
  sed -i '' "s/package $YEAR\/$NEW_DAY/package $YEAR.$NEW_DAY/g" "$file"
done

echo "Package $YEAR.$NEW_DAY and files renamed to $NEW_DAY and $NEW_FILE successfully."
