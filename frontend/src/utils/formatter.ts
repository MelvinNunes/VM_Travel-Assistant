export default class Formatter {
  static formatPopulation(population: number): string {
    if (population < 1_000_000) {
      return (population / 1_000).toFixed(0) + "k";
    } else {
      return (population / 1_000_000).toFixed(2) + "M";
    }
  }
}
