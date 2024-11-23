  // Method to adopt a pet
    private void adoptPet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow != -1) {
            int petId = (int) petTable.getValueAt(selectedRow, 0);
            String petName = (String) petTable.getValueAt(selectedRow, 1);
            
            String adopterName = adopterNameField.getText();
            
            // Check if the pet is available
            String checkPetQuery = "SELECT * FROM pets WHERE pet_id = ? AND status = 'Available'";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkPetQuery)) {
                checkStmt.setInt(1, petId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    // Pet is available, proceed with adoption
                    String sql = "INSERT INTO adoptions (pet_id, adopter_name, adoption_date) VALUES (?, ?, CURDATE())";
                    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                        pstmt.setInt(1, petId);
                        pstmt.setString(2, adopterName);
                        pstmt.executeUpdate();
                        
                        // Update pet status to adopted
                        String updateStatusSql = "UPDATE pets SET status = 'Adopted' WHERE pet_id = ?";
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateStatusSql)) {
                            updateStmt.setInt(1, petId);
                            updateStmt.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(frame, "Congrats! You have adopted " + petName);
                        loadAvailablePets(); // Reload the pet table after adoption
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Sorry, this pet is not available for adoption.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, "Error adopting pet: " + e.getMessage());
                e.printStackTrace();  // Print the stack trace for debugging
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a pet to adopt.");
        }
    }
}