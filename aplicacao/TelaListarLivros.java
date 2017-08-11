package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Livro;

public class TelaListarLivros extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scroller;
	private JButton btnListarLivros;
	private JTextField textField_1;
	private JLabel lblNome;


	/**
	 * Create the frame.
	 */
	public TelaListarLivros() {
		setTitle("Listar Livro por Titulo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroller.setBounds(24, 29, 450, 282);
		contentPane.add(scroller);
		
		lblNome = new JLabel("Titulo");
		lblNome.setBounds(20, 330, 148, 23);
		contentPane.add(lblNome);
		
		textField_1 = new JTextField();
		textField_1.setBounds(60, 330, 220, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnListarLivros = new JButton("Listar Livros");
		btnListarLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = textField_1.getText();
				String texto = "";
				ArrayList<Livro> livrosListados= new ArrayList<Livro> ();
				
				livrosListados = Fachada.listarLivrosPorFragmentoDoTitulo(nome);
				if (livrosListados == null)
					JOptionPane.showMessageDialog(null,"\nNenhum livro encontrado!\n");
				else
				{
					for (Livro l: livrosListados)
						texto+= "\nTitulo: " + l.getTitulo() + "\n" + l.getNomeDosAutores() + "\n";
					
					textArea.setText(texto);
				}
				livrosListados = null;
				
				/*********************************************/
			}
		});
		btnListarLivros.setBounds(300, 330, 148, 23);
		contentPane.add(btnListarLivros);

	}
}
