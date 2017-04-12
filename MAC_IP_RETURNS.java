package utilitarios;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import static javax.swing.JOptionPane.*;
public class PesquisaParametrosSistema 
{
	public InetAddress valorIp;
	public String nomeSistema,enderecoIp = "",enderecoMac = "";
	public PesquisaParametrosSistema()
	{
		nomeSistema = System.getProperty("os.name");
		showMessageDialog(null, "Seu Sistema é "+nomeSistema, "INformações do Sistema", INFORMATION_MESSAGE);
	}
	public String getIp()
	{
		int i = 1;
		if(nomeSistema.startsWith("Windows"))
		{
			try
			{
				valorIp = InetAddress.getLocalHost();
				enderecoIp = valorIp.getHostAddress();
			}
			catch(Exception erro)
			{
				erro.printStackTrace();
			}
		}
		if(nomeSistema.startsWith("Linux"))
		{
			try
			{
				Enumeration<NetworkInterface> enumNetWork = NetworkInterface.getNetworkInterfaces();
				while(enumNetWork.hasMoreElements())
				{
					NetworkInterface networkInterface = enumNetWork.nextElement();
					if(networkInterface.getName().startsWith("eth"))
					{
						Enumeration<InetAddress> ds = networkInterface.getInetAddresses();
						while(ds.hasMoreElements())
						{
							if(i != 1)
							{
								InetAddress myself = ds.nextElement();
								enderecoIp = myself.getHostAddress();
							}
						i++;
						}
					break;
					}
				}
			}
			catch(Exception erro)
			{
				erro.printStackTrace();
			}
		}
		return enderecoIp;
	}
	public String getMac()
	{
		int i = 1;
		if(nomeSistema.startsWith("Windows"))
		{
			try
			{
				valorIp = InetAddress.getLocalHost();
				NetworkInterface network = NetworkInterface.getByInetAddress(valorIp);
				byte[] valorMac = network.getHardwareAddress();
				for(i = 0; i <valorMac.length;i++)
				{
					enderecoMac += (String.format("%02X%s",valorMac[i], (i < valorMac.length - 1) ? "-" : ""));
				}
			}
			catch(Exception erro)
			{
				erro.printStackTrace();
			}
		}
		if(nomeSistema.startsWith("Linux"))
		{
			try
			{
				Enumeration<NetworkInterface> enumNetWork = NetworkInterface.getNetworkInterfaces();
				while(enumNetWork.hasMoreElements())
				{
					NetworkInterface networkInterface = enumNetWork.nextElement();
					if(networkInterface.getName().startsWith("eth"))
					{
						Enumeration<InetAddress> ds = networkInterface.getInetAddresses();
						while(ds.hasMoreElements())
						{
							if(i != 2)
							{
								for (int o = 0; o < networkInterface.getHardwareAddress().length; o++) {
			            				enderecoMac += (String.format("%02X%s", networkInterface.getHardwareAddress()[o], (o < networkInterface.getHardwareAddress().length - 1) ? "-" : ""));		
			            		 }
							break;
							}
						i++;
						}
					break;
					}
				}
			}
			catch(Exception erro)
			{
				erro.printStackTrace();
			}
		}
		return enderecoMac;
	}
}