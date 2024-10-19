class CourtPartModel {
  String partName;
  List<int> colorThresholds;
  bool isDisabled;
  int shotsRequired;
  int shotsSuccess = 0;

  CourtPartModel(
      this.partName, this.colorThresholds, this.isDisabled, this.shotsRequired);

}
