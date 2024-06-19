export class TemperatureUtils {
  static colorSelector(temperature: number) {
    switch (true) {
      case temperature < 0:
        return "text-red-600"; // Light Blue
      case temperature < 10:
        return "text-orange-600"; // Blue
      case temperature < 20:
        return "text-red-600"; // Light Green
      case temperature < 30:
        return "text-orange-600"; // Yellow
      default:
        return "text-red-300"; // Red
    }
  }
}
