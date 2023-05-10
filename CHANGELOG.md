# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- LASRV014 - 03/05/2023 - Add scripts, service and config file.

### Changed
- LASRV007 - 03/05/2023 - Not see root process.
- LASRV009 - 03/05/2023 - Put autoremove.
- LASRV006 - 03/05/2023 - Not see password.
- LASRV014 - 03/05/2023 - Check root login.
- LASRV001 - 07/05/2023 - Change the obtain CPU use.
- LASRV001 - 07/05/2023 - Fix the obtain CPU use.
- LASRV005 - 09/05/2023 - Fix Arch version errors.
- LASRV014 - 09/05/2023 - Fix Arch install errors.
- LASRV014 - 10/05/2023 - Fix no response when close client.

### Fixed
- LASRVUCF - 29/04/2023 - Quit server file.
- LASRV011 - 29/04/2023 - Fix close the streams of socket.
- LASRV011 - 29/04/2023 - Fix small RSA key.
- LASRV011 - 29/04/2023 - Fix infinite while in connection when have I/O error.
- LASRV002 - 29/04/2023 - Fix model error in GPU.
- LASRV008 - 30/04/2023 - Fix gets event command.
- LASRV008 - 30/04/2023 - Fix modify event command.
- LASRV008 - 30/04/2023 - Fix delete event command.
- LASRV006 - 01/05/2023 - Fix add and modify users.
- LASRV007 - 01/05/2023 - Fix kill process.
- LASRV009 - 01/05/2023 - Fix manage APT packages.
- LASRV006 - 03/05/2023 - Actions in user not send good response.
- LASRV004 - 03/05/2023 - Fix obtain RAM use.
- LASRV004 - 03/05/2023 - Fix obtain RAM unit capacity.
- LASRV005 - 03/05/2023 - Fix float versions.
- LASRV009 - 03/05/2023 - Fix list packages in zypper and yum.
- LASRV014 - 03/05/2023 - Fix not have crontab permissions.
- LASRV006 - 09/05/2023 - Fix user password.

### Removed

## [0.2]
### Added
- LASRV010 - 01/04/2023 - Add the Request and Response class.
- LASRV010 - 01/04/2023 - Add the exception ConnectionException.
- LASRV010 - 02/04/2023 - Add the CommunicatorBase interface.
- LASRV010 - 03/04/2023 - Add the AESCommunicator class.
- LASRV010 - 03/04/2023 - Add the RequestManager class.
- LASRV010 - 03/04/2023 - Add the RequestCPU class.
- LASRV010 - 03/04/2023 - Add the RequestRAM class.
- LASRV010 - 04/04/2023 - Add the RequestGPU class.
- LASRV010 - 04/04/2023 - Add the RequestPartition class.
- LASRV010 - 04/04/2023 - Add the RequestOS class.
- LASRV010 - 04/04/2023 - Add the RequestProcess class.
- LASRV010 - 04/04/2023 - Add the RequestUser class.
- LASRV010 - 04/04/2023 - Add the RequestEvent class.
- LASRV010 - 04/04/2023 - Add the RequestPackage class.
- LASRV010 - 04/04/2023 - Add the RaspberryDetector class.
- LASRV011 - 04/04/2023 - Add the ClientManager class.
- LASRV011 - 04/04/2023 - Add the AuthUser class.
- LASRV011 - 05/04/2023 - Add the RSAGenerator class.
- LASRV011 - 05/04/2023 - Add the RSACommunicator class.
- LASRV011 - 05/04/2023 - Add the PlainCommunicator class.
- LASRV011 - 10/04/2023 - Add the EstablishConnection class.
- LASRV011 - 10/04/2023 - Add the ConnectionListener class.
- LASRV012 - 11/04/2023 - Add the LogController class.
- LASRV013 - 11/04/2023 - Add the ConfigController class.

### Changed
- LASRV010 - 03/04/2023 - Changes in Request class.
- LASRV010 - 03/04/2023 - Serialice class who send to the client.
- LASRV010 - 04/04/2023 - Changes in the manage of response in the requests class.
- LASRV005 - 04/04/2023 - Changes in the OSInformation, it have the package manager components.
- LASRV010 - 05/04/2023 - Refractor in the communicators exceptions.
- LASRV012 - 11/04/2023 - Implements LogController class in the program.
- LASRV012 - 11/04/2023 - Changes when create the log file.
- LASRV011 - 12/04/2023 - Implements the AuthUser class in the program.
- LASRV013 - 12/04/2023 - Implements the ConfigController class in the program.
- LASRV010 - 12/04/2023 - Change CommunicatorBase from interface to abstract class.
- LASRV011 - 12/04/2023 - Change in obtain the package manager in the connetion class.
- LASRV010 - 12/04/2023 - Change in exceptions of communicators.
- LASRV012 - 12/04/2023 - Change in the logs message.
- LASRV011 - 12/04/2023 - Defend the ClientManager of bad request objects.
- LASRV010 - 12/04/2023 - Synchronize critical parts.
- LASRV011 - 12/04/2023 - Close the socket with SIGINT signal.

### Fixed
- LASRV012 - 12/04/2023 - Improve file organization in logs.
- LASRV013 - 12/04/2023 - Improve file organization in config.

### Removed
- LASRV010 - 05/04/2023 - Removed the send and obtain exceptions.

## [0.1]
### Added
- LASRV001 - 20/03/2023 - Add the class to exec commands and the response object.
- LASRV001 - 21/03/2023 - Add CPUInfo class to obtain information of CPU.
- LASRV001 - 21/03/2023 - Add temperature classes to obtain the temperature of CPU.
- LASRV001 - 21/03/2023 - Add an class which detects the device to return the correct temperature manager.
- LASRV001 - 21/03/2023 - Add the UseCPU class to obtain uses of CPU.
- LASRV001 - 22/03/2023 - Add an use device detector to return the correct usage manager.
- LASRV001 - 22/03/2023 - Add the CPUManager class to obtain CPU.
- LASRV002 - 22/03/2023 - Add GPUInfo class to obtain information of GPU.
- LASRV002 - 22/03/2023 - Add GPUManager class to obtain GPU.
- LASRV003 - 22/03/2023 - Add RAMInfo class to obtain information of RAM.
- LASRV003 - 23/03/2023 - Add the UseRAM class to obtain information of RAM.
- LASRV003 - 23/03/2023 - Add the RAMManager class to obtain the RAM.
- LASRV004 - 23/03/2023 - Add the PartitonInfo class to obtain information of partitions.
- LASRV004 - 24/03/2023 - Add the PartitionManager class to obtain partitions.
- LASRV005 - 25/03/2023 - Add the UnameInfo class to obtain hostame and kernel.
- LASRV005 - 25/03/2023 - Add the DistributionInfo class to obtain information of distribution.
- LASRV005 - 25/03/2023 - Add the OSManager class to obtain information of OS.
- LASRV006 - 26/03/2023 - Add the UserInfo class to obtain information of user.
- LASRV006 - 26/03/2023 - Add the RootPermissionInfo class to obtain if user have root permission.
- LASRV006 - 26/03/2023 - Add the UserModificator class to modify user.
- LASRV006 - 26/03/2023 - Add the PasswordModificator class to modify password of user.
- LASRV006 - 26/03/2023 - Add the UserInsertion class to add users.
- LASRV006 - 26/03/2023 - Add the UserDeletion class to delete users.
- LASRV006 - 26/03/2023 - Add the UserManager class to manage users.
- LASRV007 - 27/03/2023 - Add the ProcessInfo class to get information of process.
- LASRV007 - 27/03/2023 - Add the ProcessManager class to get process object.
- LASRV007 - 27/03/2023 - Add the ProcessDeletion class to kill process.
- LASRV008 - 28/03/2023 - Add the EventInformation class to get information of event.
- LASRV008 - 28/03/2023 - Add the EventInsertion class to insert event.
- LASRV008 - 28/03/2023 - Add the EventModificator class to modify event.
- LASRV008 - 28/03/2023 - Add the EventDeletion class to delete event.
- LASRV008 - 28/03/2023 - Add the EventManager class to manage event objects.
- LASRV009 - 29/03/2023 - Add the PackageInsertion class to install packages.
- LASRV009 - 30/03/2023 - Add the PackageModificator class to update or upgrade packages.
- LASRV009 - 30/03/2023 - Add the PackageDeletion class to uninstall packages.
- LASRV009 - 30/03/2023 - Add the PackageInfo class to get info of packages.
- LASRV009 - 30/03/2023 - Add the PackageManager class to inetact to the package manager.
- LASRV009 - 30/03/2023 - Add the PackageManagerDetector class to detect the package manager.

### Changed
- LASRV001 - 21/03/2023 - Refactor code in the temperature detectors.
- LASRV001 - 25/03/2023 - Quit CPUException in not important information of CPU.
- LASRVUCF - 28/03/2023 - Change the manager exceptions to give a standard description.
- LASRV008 - 29/03/2023 - Refactor the exception message of the ManagerException.
- LASRV001 - 29/03/2023 - Add components enum in CPUInfo.
- LASRV003 - 29/03/2023 - Add components enum in RAMInfo.
- LASRV002 - 29/03/2023 - Add components enum in GPUInfo.

### Fixed
- LASRV006 - 26/03/2023 - Fix add user bug.

### Removed
- LASRV001 - 29/03/2023 - Quit the device string in temperature and use managers.

[Unreleased]: https://github.com/Lagatrix/Lagatrix-Server
[0.2]: https://github.com/Lagatrix/Lagatrix-Server/releases/tag/0.2
[0.1]: https://github.com/Lagatrix/Lagatrix-Server/releases/tag/0.1
