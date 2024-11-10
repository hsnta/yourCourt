import 'package:flutter/material.dart';

class TagsFilter extends StatelessWidget {
  final List<String> tags;
  final Set<String> selectedTags;
  final ValueChanged<String> onTagSelected;

  const TagsFilter({
    Key? key,
    required this.tags,
    required this.selectedTags,
    required this.onTagSelected,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Wrap(
      spacing: 8.0,
      runSpacing: 8.0,
      children: tags.map((tag) {
        final isSelected = selectedTags.contains(tag);
        return FilterChip(
          label: Text(tag),
          selected: isSelected,
          onSelected: (selected) {
            if (selected) {
              onTagSelected(tag);
            } else {
              onTagSelected(tag); // Handle deselection in the parent widget
            }
          },
        );
      }).toList(),
    );
  }
}
