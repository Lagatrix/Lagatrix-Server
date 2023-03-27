# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- LASRV001 - 20/03/2023 - Create the class to exec commands and the response object.
- LASRV001 - 21/03/2023 - Create CPUInfo class to obtain information of CPU.
- LASRV001 - 21/03/2023 - Create temperature classes to obtain the temperature of CPU.
- LASRV001 - 21/03/2023 - Create an class which detects the device to return the correct temperature manager.
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

### Fixed
- LASRV001 - 21/03/2023 - Refactor code in the temperature detectors.
- LASRV001 - 25/03/2023 - Quit CPUException in not important information of CPU.
- LASRV006 - 26/03/2023 - Fix add user bug.

[unreleased]: https://github.com/Lagatrix/Lagatrix-Server
