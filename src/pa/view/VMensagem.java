package pa.view;

import pa.Cor;

public class VMensagem 
{
	public static String erro(String mensagem)
	{
		return " " + Cor.vermelha() + mensagem + Cor.reset();
	}
}
