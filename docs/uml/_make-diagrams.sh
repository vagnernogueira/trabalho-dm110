#!/bin/bash

if ! command -v plantuml &> /dev/null
then
    echo "Error: 'plantuml' not found."
    exit 1
fi

SOURCE_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

if [ ! -d "$SOURCE_DIR" ]; then
  echo "Error: Directory '$SOURCE_DIR' not found ou invalid."
  exit 1
fi

find "$SOURCE_DIR" -maxdepth 1 -type f -name "*.puml" -print0 | while IFS= read -r -d $'\0' puml_file; do
  if [ -f "$puml_file" ]; then
    echo "Process file: '$puml_file'"
    plantuml -tsvg "$puml_file"
    if [ $? -eq 0 ]; then
      echo "  -> Image generated successfully."
    else
      echo "  -> Error generating image for '$puml_file'. Check file syntax or PlantUML output."
    fi
  fi
done

echo "Done!"

exit 0