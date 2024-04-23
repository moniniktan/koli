import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		File file = new File("insurances.txt");

		Scanner input = null;
		Map<String, Integer> numberOfInsurances = new HashMap<>();
		Map<String, Set<String>> vinOwners = new HashMap<>();
		Map<String, Car> mostRecentRegNumber = new HashMap<>();

		try {
			input = new Scanner(file);

			while (input.hasNext()) {
				String vin = input.next();
				String regNumber = input.next();
				int year = input.nextInt();
				String dateOfInsurance = input.next();
				String ownerFirstName = input.next();
				String ownerLastName = input.next();
				String owner = ownerFirstName + " " + ownerLastName;
				Car car = new Car(vin, regNumber, year, dateOfInsurance, ownerFirstName, ownerLastName);

				vinOwners.computeIfAbsent(vin, k -> new HashSet<>());
				vinOwners.get(vin).add(owner);

				if(numberOfInsurances.containsKey(vin)) {
					numberOfInsurances.put(vin, numberOfInsurances.get(vin)+1);
				} else {
					numberOfInsurances.put(vin, 1);
				}

				if(!mostRecentRegNumber.containsKey(vin)
						|| (mostRecentRegNumber.containsKey(vin)
						&& car.getDateOfInsurance().compareTo(mostRecentRegNumber.get(vin).getDateOfInsurance()) > 0)) {
					mostRecentRegNumber.put(vin, car);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			assert input != null;
			input.close();
		}

		numberOfInsurances = sortByValue(numberOfInsurances);

		List<String> top5 = new ArrayList<>();
		for(Map.Entry<String, Integer> el : numberOfInsurances.entrySet()) {
			if (top5.size() == 5) break;
			top5.add(el.getKey());
		}

		Map<String, Integer> top5Owners = new HashMap<>();
		for(Map.Entry<String, Set<String>> el : vinOwners.entrySet()) {
			for(String vin : top5) {
				if (el.getKey().equals(vin)) {
					top5Owners.put(el.getKey(), el.getValue().size());
					break;
				}
			}
		}

		top5Owners = sortByValue(top5Owners);

		File file2 = new File("output.txt");
		if (file.exists()) {
			System.out.println("File already exists");
			System.exit(1);
		}
		PrintWriter output = null;

		try {
			output = new PrintWriter(file2);

			for(String vin : top5Owners.keySet()) {
				output.print(mostRecentRegNumber.get(vin).getRegNumber() + " ");
				output.print(top5Owners.get(vin) + " owners");
				output.print(numberOfInsurances.get(vin) + " insurances");
				output.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			assert output != null;
			output.close();
		}
	}

	private static HashMap<String, Integer> sortByValue(Map<String, Integer> hm)
	{
		List<Map.Entry<String, Integer> > list =
				new LinkedList<>(hm.entrySet());

		list.sort(new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		HashMap<String, Integer> temp = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> el : list) {
			temp.put(el.getKey(), el.getValue());
		}
		return temp;
	}
}
