package TcsCodeVita2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class A {
    static class Command {
        int existing;
        int added;
        String direction;

        Command(int existing, int added, String direction) {
            this.existing = existing;
            this.added = added;
            this.direction = direction;
        }
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Position)) {
                return false;
            }
            Position position = (Position) other;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        
        int commandCount = Integer.parseInt(line.trim());
        if (commandCount == -1) {
            return;
        }

        List<Command> commands = new ArrayList<>();
        Set<Integer> existingCubes = new HashSet<>();
        Set<Integer> newCubes = new HashSet<>();
        
        for (int i = 0; i < commandCount; i++) {
            line = br.readLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line.trim());
            if (st.countTokens() < 3) continue;
            
            int existing = Integer.parseInt(st.nextToken());
            int added = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            
            commands.add(new Command(existing, added, direction));
            existingCubes.add(existing);
            newCubes.add(added);
        }

        // If no commands were given, print the default state and end with exactly \n
        if (commands.isEmpty()) {
            System.out.print("-1 -1 -1 -1\n");
            return;
        }
        
        // Read the target cube safely, ignoring any empty blank lines
        line = br.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        if (line == null) {
            System.out.print("-1 -1 -1 -1\n");
            return;
        }
        int target = Integer.parseInt(line.trim());

        // Sort existing elements dynamically 
        commands.sort(Comparator.comparingInt((Command command) -> command.existing)
                .thenComparingInt(command -> command.added));

        // Locate origin block
        int root = Integer.MAX_VALUE;
        for (int cube : existingCubes) {
            if (!newCubes.contains(cube) && cube < root) {
                root = cube;
            }
        }
        if (root == Integer.MAX_VALUE) {
            root = commands.get(0).existing;
        }

        Map<Integer, Position> positionOfCube = new HashMap<>();
        Map<Position, Integer> cubeAtPosition = new HashMap<>();
        Position origin = new Position(0, 0);
        
        positionOfCube.put(root, origin);
        cubeAtPosition.put(origin, root);

        for (Command command : commands) {
            Position base = positionOfCube.get(command.existing);
            if (base == null) {
                continue;
            }

            int dx = 0;
            int dy = 0;
            switch (command.direction.toLowerCase()) {
                case "top":
                case "up":
                    dy = 1;
                    break;
                case "down":
                    dy = -1;
                    break;
                case "left":
                    dx = -1;
                    break;
                case "right":
                    dx = 1;
                    break;
                default:
                    continue;
            }

            Position destination = new Position(base.x + dx, base.y + dy);
            Position previousPosition = positionOfCube.remove(command.added);
            
            // Clean up potentially overwritten or outdated positional trackers
            if (previousPosition != null) {
                Integer cubeAtPrev = cubeAtPosition.get(previousPosition);
                if (cubeAtPrev != null && cubeAtPrev.equals(command.added)) {
                    cubeAtPosition.remove(previousPosition);
                }
            }

            Integer replacedCube = cubeAtPosition.put(destination, command.added);
            if (replacedCube != null) {
                positionOfCube.remove(replacedCube);
            }
            positionOfCube.put(command.added, destination);
        }

        Position targetPosition = positionOfCube.get(target);
        if (targetPosition == null) { 
            System.out.print("-1 -1 -1 -1\n");
            return;
        }

        // Fetch surrounding blocks relative to target
        int up = cubeAtPosition.getOrDefault(new Position(targetPosition.x, targetPosition.y + 1), -1);
        int down = cubeAtPosition.getOrDefault(new Position(targetPosition.x, targetPosition.y - 1), -1);
        int left = cubeAtPosition.getOrDefault(new Position(targetPosition.x - 1, targetPosition.y), -1);
        int right = cubeAtPosition.getOrDefault(new Position(targetPosition.x + 1, targetPosition.y), -1);
        
        // Exact POSIX-compliant output format
        System.out.print(up + " " + down + " " + left + " " + right + "\n");
    }
}